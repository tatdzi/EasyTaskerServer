package by.taskManager.reportservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.reportservice.core.dto.FileDTO;
import by.taskManager.reportservice.core.dto.ReportType;

import java.util.Map;
import java.util.UUID;

public interface IReportService {
    UUID create(Map<String,Object> param, ReportType type);
    PageDTO getPage(Integer page, Integer size);
    String download(UUID uuid);
    boolean isReady(UUID uuid);
}
