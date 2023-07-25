package by.taskManager.auditservice.dao.api;

import by.taskManager.auditservice.dao.entity.AuditEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IAuditData extends CrudRepository<AuditEntity, UUID> {
}
