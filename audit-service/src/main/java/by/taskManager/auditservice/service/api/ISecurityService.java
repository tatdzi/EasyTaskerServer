package by.taskManager.auditservice.service.api;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.PageDTO;

import java.util.UUID;

public interface ISecurityService {
    void save(AuditDTO audit);
    PageDTO getPage(Integer page, Integer size);
    AuditDTO getCard(UUID uuid);
}
