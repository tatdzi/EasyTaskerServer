package by.taskManager.auditservice.dao.api;

import by.taskManager.auditservice.dao.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IAuditData extends JpaRepository<AuditEntity, UUID>, PagingAndSortingRepository<AuditEntity, UUID> {
}
