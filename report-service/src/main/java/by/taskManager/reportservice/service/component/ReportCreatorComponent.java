package by.taskManager.reportservice.service.component;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;
import by.taskManager.reportservice.core.dto.ReportStatus;
import by.taskManager.reportservice.dao.entity.Report;
import by.taskManager.reportservice.service.api.IAuditService;
import by.taskManager.reportservice.service.api.IMinioService;
import by.taskManager.reportservice.service.api.IReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class ReportCreatorComponent {
    private IReportService reportService;
    private ConversionService conversionService;
    private IAuditService auditService;
    private IMinioService minioService;

    public ReportCreatorComponent(IReportService reportService,
                                  ConversionService conversionService,
                                  IMinioService minioService,
                                  IAuditService auditService) {
        this.reportService = reportService;
        this.conversionService = conversionService;
        this.minioService = minioService;
        this.auditService = auditService;
    }

    @Scheduled(fixedDelay = 30000)
    public void performScheduledReportFormingAndSending() {
        List<Report> reports = reportService.getList(ReportStatus.LOADED);
        for (Report report : reports) {
            report.setStatus(ReportStatus.PROGRESS);
            reportService.save(report);
            formAndSendReport(report);
            Report reportDone = reportService.get(report.getUuid());
            reportDone.setStatus(ReportStatus.DONE);
            reportService.save(reportDone);
        }
    }

    private void formAndSendReport(Report report) {
        Map<String, String> params = report.getParam();
        try {
            ReportParamAudit reportParamAudit = (ReportParamAudit) conversionService.convert(
                    params,
                    TypeDescriptor.valueOf(Map.class),
                    TypeDescriptor.valueOf(ReportParamAudit.class)
            );
            List<AuditDTO> auditDTOList = auditService.getList(reportParamAudit);
            File fileReport = createFileWithReports(auditDTOList, report.getUuid().toString());
            minioService.upload(fileReport);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private File createFileWithReports(List<AuditDTO> auditDTOList,String nameFile) throws IOException {
        File report = new File(nameFile+".xlsx");
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("report");
            int rowNum = 0;
            createTopRow(workbook, sheet, rowNum++);
            createHeaderRow(workbook, sheet, rowNum++);
            for (AuditDTO auditDTO : auditDTOList) {
                fillRowWithData(auditDTO, workbook, sheet, rowNum++);
            }
            for (int i = 0; i < 9; i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(report)) {
                workbook.write(fileOutputStream);
                workbook.close();
            }
            return report;
        }
    }

    private void fillRowWithData(AuditDTO auditDTO, Workbook workbook, Sheet sheet, int rowNum) {
        Row contextRow = sheet.createRow(rowNum);
        contextRow.createCell(0).setCellValue(auditDTO.getUuid().toString());
        CellStyle dtUpdateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dtUpdateCellStyle.setDataFormat(
                creationHelper.createDataFormat().getFormat("dd-mm-yyyy h:mm:ss"));
        Cell dtUpdateCell = contextRow.createCell(1);
        dtUpdateCell.setCellStyle(dtUpdateCellStyle);
        LocalDateTime dtCreate = auditDTO.getDtCreate();
        dtUpdateCell.setCellValue(dtCreate);
        contextRow.createCell(2).setCellValue(auditDTO.getUser().getUuid().toString());
        contextRow.createCell(3).setCellValue(auditDTO.getUser().getMail());
        contextRow.createCell(4).setCellValue(auditDTO.getUser().getFio());
        contextRow.createCell(5).setCellValue(auditDTO.getUser().getRole().toString());
        contextRow.createCell(6).setCellValue(auditDTO.getText());
        contextRow.createCell(7).setCellValue(auditDTO.getType().toString());
        contextRow.createCell(8).setCellValue(auditDTO.getId());
    }

    private void createHeaderRow(Workbook workbook, Sheet sheet, int rowNum) {
        Row headerRow = sheet.createRow(rowNum);
        headerRow.createCell(0).setCellValue("uuid");
        headerRow.createCell(1).setCellValue("dt_create");
        headerRow.createCell(2).setCellValue("uuid");
        headerRow.createCell(3).setCellValue("mail");
        headerRow.createCell(4).setCellValue("fio");
        headerRow.createCell(5).setCellValue("role");
        headerRow.createCell(6).setCellValue("text");
        headerRow.createCell(7).setCellValue("type");
        headerRow.createCell(8).setCellValue("id");
    }

    private void createTopRow(Workbook workbook, Sheet sheet, int rowNum) {
        Row userHeaderRow = sheet.createRow(rowNum);
        Cell userCell = userHeaderRow.createCell(2);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        userCell.setCellStyle(cellStyle);
        userCell.setCellValue("user");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 5));

    }
}
