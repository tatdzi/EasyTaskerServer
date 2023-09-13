package by.taskManager.reportservice.service;

import by.TaskManeger.utils.dto.PageDTO;
import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.reportservice.core.dto.*;
import by.taskManager.reportservice.dao.api.IReportData;
import by.taskManager.reportservice.dao.entity.Report;
import by.taskManager.reportservice.service.api.IReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ReportService implements IReportService {
    private IReportData reportData;

    public ReportService(IReportData reportData) {
        this.reportData = reportData;
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

            Report report = new Report();
            report.setUuid(UUID.randomUUID());
            report.setType(type);
            if (param.containsKey("from") && param.containsKey("to")) {
                report.setDescription("Журнал Аудита за : " + param.get("from") + " - " + param.get("to"));
            }
            report.setStatus(ReportStatus.LOADED);
            reportData.save(report);
            return report.getUuid();
        }
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
        return null;
    }

    @Override
    public boolean isReady(UUID uuid) {
        return true;
    }
}
