package by.taskManager.auditservice.dao.entity;



import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.EssenceType;
import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "audit")
public class AuditEntity {
    @Id
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "user_uuid")
    private String userUuid;
    @Column(name = "user_mail")
    private String userMail;
    @Column(name = "user_fio")
    private String userFio;
    @Column(name = "user_role")
    private UserRole userRole;

    private String text;
    private EssenceType type;
    private String id;

    public AuditEntity() {
    }

    public AuditEntity(AuditDTO dto) {
        this.uuid = dto.getUuid();
        this.dtCreate = dto.getDtCreate();
        this.userUuid = dto.getUser().getUuid();
        this.userMail = dto.getUser().getMail();
        this.userFio = dto.getUser().getFio();
        this.userRole = dto.getUser().getRole();
        this.text = dto.getText();
        this.type = dto.getType();
        this.id = dto.getId();
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserFio() {
        return userFio;
    }

    public UserRole getUserRole() {
        return userRole;
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
