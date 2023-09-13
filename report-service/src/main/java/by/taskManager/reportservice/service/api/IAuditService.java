package by.taskManager.reportservice.service.api;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;

import java.util.List;

public interface IAuditService {
    List<AuditDTO> getList(ReportParamAudit paramAudit);
}
