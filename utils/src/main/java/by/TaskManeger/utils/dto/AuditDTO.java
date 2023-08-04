package by.TaskManeger.utils.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class AuditDTO {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private TokenDTO user;
    private String text;
    private EssenceType type;
    private String id;

    public AuditDTO() {
    }
    public AuditDTO(UUID uuid, LocalDateTime dtCreate, TokenDTO user, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }
    public void setUser(TokenDTO user) {
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
    public UUID getUuid() {
        return uuid;
    }
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }
    public TokenDTO getUser() {
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
