package by.taskManager.auditservice.dao.spetifications;

import by.taskManager.auditservice.dao.entity.AuditEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditSpecifications {
    public static Specification<AuditEntity> byDateFrom(LocalDateTime from) {
        return (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("createDate"), from);
    }
    public static Specification<AuditEntity> byDateTo(LocalDateTime to) {
        return (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get("createDate"), to);
    }
    public static Specification<AuditEntity> byUser(UUID user) {
        return (root, query, builder) ->
                builder.equal(root.get("userId"), user);
    }
}
