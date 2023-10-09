package by.taskManager.reportservice.service;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.reportservice.core.dto.*;
import by.taskManager.reportservice.dao.api.IReportData;
import by.taskManager.reportservice.dao.entity.Report;
import by.taskManager.reportservice.service.api.IMinioService;
import by.taskManager.reportservice.service.api.IReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
@Transactional(readOnly = true)
public class ReportService implements IReportService {
    private IReportData reportData;
    private IMinioService minioService;

    public ReportService(IReportData reportData,IMinioService minioService) {
        this.reportData = reportData;
        this.minioService = minioService;
    }
    @Transactional
    @Override
    public UUID save(Report report) {
        reportData.saveAndFlush(report);
        return report.getUuid();
    }

    @Transactional
    @Override
    public UUID create(Map<String, String> param, ReportType type) {
        if(type.equals(ReportType.JOURNAL_AUDIT)) {
                Report report = new Report();
                report.setUuid(UUID.randomUUID());
                report.setType(type);
                report.setDiscription(null);
                report.setParam(param);
                if (param.containsKey("from") && param.containsKey("to")) {
                    report.setDiscription("Журнал Аудита за : " + param.get("from") + " - " + param.get("to"));
                }
                report.setStatus(ReportStatus.LOADED);
                reportData.save(report);
                return report.getUuid();
        }
        throw new RuntimeException("not so type report");
    }

    @Override
    public Report get(UUID uuid) {
        Report report = reportData.findById(uuid).orElseThrow(() ->
                new RuntimeException("Задача не найдена"));
        return report;
    }

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
    public String download(UUID uuid) {
        Report report = this.reportData.findById(uuid)
                .orElseThrow(() -> new RuntimeException("123"));
        String fileName = report.getUuid().toString()+".xlsx";
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

    @Override
    public List<Report> getList(ReportStatus status) {
        return reportData.findByStatus(status);
    }
}


