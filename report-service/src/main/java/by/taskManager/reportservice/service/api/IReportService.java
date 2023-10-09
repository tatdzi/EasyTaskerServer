package by.taskManager.reportservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.reportservice.core.dto.FileDTO;
import by.taskManager.reportservice.core.dto.ReportStatus;
import by.taskManager.reportservice.core.dto.ReportType;
import by.taskManager.reportservice.dao.entity.Report;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IReportService {
    UUID save(Report report);
    UUID create(Map<String,String> param, ReportType type);
    Report get(UUID uuid);
    PageDTO getPage(Integer page, Integer size);
    String download(UUID uuid);
    boolean isReady(UUID uuid);
    List<Report> getList(ReportStatus status);
}
