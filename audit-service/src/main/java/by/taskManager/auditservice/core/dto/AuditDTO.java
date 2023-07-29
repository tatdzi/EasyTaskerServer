package by.taskManager.auditservice.core.dto;

import by.taskManager.auditservice.dao.entity.AuditEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditDTO {
    private UUID uuid;
    private LocalDateTime dt_create;
    private UserDTO user;
    private String text;
    private EssenceType type;
    private String id;

    public AuditDTO(UUID uuid, LocalDateTime dt_create, UserDTO user, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public AuditDTO(AuditEntity entity) {
        this.uuid = entity.getUuid();
        this.dt_create = entity.getDt_create();
        this.user = entity.getUser();
        this.text = entity.getText();
        this.type = entity.getType();
        this.id = entity.getId();
    }



    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public UserDTO getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public EssenceType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
