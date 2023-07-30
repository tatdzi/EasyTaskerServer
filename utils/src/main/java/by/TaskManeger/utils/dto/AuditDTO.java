package by.TaskManeger.utils.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class AuditDTO {
    private UUID uuid;
    private LocalDateTime dt_create;
    private TokenDTO user;
    private String text;
    private EssenceType type;
    private String id;

    public AuditDTO(UUID uuid, LocalDateTime dt_create, TokenDTO user, String text, EssenceType type, String id) {
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
