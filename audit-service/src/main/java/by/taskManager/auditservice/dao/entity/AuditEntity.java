package by.taskManager.auditservice.dao.entity;

import by.taskManager.auditservice.core.dto.EssenceType;
import by.taskManager.auditservice.core.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditEntity {
    private UUID uuid;
    private LocalDateTime dt_create;
    private UserDTO user;
    private String text;
    private EssenceType type;
    private String id;

    public AuditEntity() {
    }

    public AuditEntity(UUID uuid, LocalDateTime dt_create, UserDTO user, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }
}
