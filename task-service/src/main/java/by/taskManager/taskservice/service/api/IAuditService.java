package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.AuditDTO;

public interface IAuditService {
    void saveItem(AuditDTO audit);
}
