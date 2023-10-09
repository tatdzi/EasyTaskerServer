package by.taskManager.auditservice.dao.api;

import by.taskManager.auditservice.dao.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface IAuditData extends JpaRepository<AuditEntity, UUID>,
        PagingAndSortingRepository<AuditEntity, UUID>,
        JpaSpecificationExecutor<AuditEntity> {
}
