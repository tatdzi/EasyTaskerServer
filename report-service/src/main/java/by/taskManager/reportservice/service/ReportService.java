package by.taskManager.reportservice.service;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.PageDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;
import by.taskManager.reportservice.core.dto.*;
import by.taskManager.reportservice.dao.api.IReportData;
import by.taskManager.reportservice.dao.entity.Report;
import by.taskManager.reportservice.service.api.IAuditService;
import by.taskManager.reportservice.service.api.IMinioService;
import by.taskManager.reportservice.service.api.IReportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ReportService implements IReportService {
    private IReportData reportData;
    private IAuditService auditService;
    private IMinioService minioService;

    public ReportService(IReportData reportData,IAuditService auditService,IMinioService minioService) {
        this.reportData = reportData;
        this.auditService = auditService;
        this.minioService = minioService;
    }
    @Transactional
    @Override
    public UUID create(Map<String, Object> param, ReportType type) {
        if(type.equals(ReportType.JOURNAL_AUDIT)) {
            ReportParamAudit params = new ReportParamAudit(
                    (UUID)param.get("uuid"),
                    (LocalDateTime) param.get("from"),
                    (LocalDateTime)param.get("to")
            );
            List<AuditDTO> auditDTOList = auditService.getList(params);
            Report report = new Report();
            report.setUuid(UUID.randomUUID());
            report.setType(type);
            if (param.containsKey("from") && param.containsKey("to")) {
                report.setDescription("Журнал Аудита за : " + param.get("from") + " - " + param.get("to"));
            }
            report.setStatus(ReportStatus.LOADED);
            File reportFile = generateAudit(auditDTOList,report.getUuid());
            minioService.upload(reportFile);
            reportData.save(report);
            return report.getUuid();
        }
        return null;
    }
    @Transactional(readOnly = true)
    @Override
    public PageDTO getPage(Integer page, Integer size) {
        Page<Report> pageResponse = reportData.findAll(PageRequest.of(page, size));
        List<ReportDTO> content = new ArrayList<>();
        for (Report entity:pageResponse){
            content.add(new ReportDTO(entity));
        }
        return new PageDTO<>(pageResponse,content);
    }

    @Override
    public FileDTO download(UUID uuid) {
        Report report = this.reportData.findById(uuid)
                .orElseThrow(() -> new RuntimeException());
        String fileName = report.getUuid().toString();
        return this.minioService.download(fileName);
    }

    @Override
    public boolean isReady(UUID uuid) {
        Report report = this.reportData.findById(uuid)
                .orElseThrow(RuntimeException::new);
        if (!report.getStatus().equals(ReportStatus.DONE)){
            return false;
        }
        return true;
    }

    public File generateAudit(List<AuditDTO> audit, UUID name) {
        File reportFile = new File(name + ".xlsx");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Audit Report");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("UUID");
            headerRow.createCell(1).setCellValue("User");
            headerRow.createCell(2).setCellValue("Mail");
            headerRow.createCell(3).setCellValue("Fio");
            headerRow.createCell(4).setCellValue("Role");
            headerRow.createCell(5).setCellValue("Text");
            headerRow.createCell(6).setCellValue("Type");
            headerRow.createCell(7).setCellValue("ID");
            headerRow.createCell(8).setCellValue("Date Created");

            for (int i = 0; i < audit.size(); i++) {
                AuditDTO auditDTO = audit.get(i);
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(auditDTO.getUuid().toString());
                dataRow.createCell(1).setCellValue(auditDTO.getUser().getUuid().toString());
                dataRow.createCell(2).setCellValue(auditDTO.getUser().getMail());
                dataRow.createCell(3).setCellValue(auditDTO.getUser().getFio());
                dataRow.createCell(4).setCellValue(auditDTO.getUser().getRole().toString());
                dataRow.createCell(5).setCellValue(auditDTO.getText());
                dataRow.createCell(6).setCellValue(auditDTO.getType().toString());
                dataRow.createCell(7).setCellValue(auditDTO.getId());
                dataRow.createCell(8).setCellValue(auditDTO.getDtCreate());
            }
            try (FileOutputStream fos = new FileOutputStream(reportFile)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return reportFile;
    }
}


