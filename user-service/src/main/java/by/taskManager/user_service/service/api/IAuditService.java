package by.taskManager.user_service.service.api;

import by.TaskManeger.utils.dto.AuditDTO;
import org.springframework.http.HttpStatus;

public interface IAuditService {
    void saveItem(AuditDTO audit);
}
