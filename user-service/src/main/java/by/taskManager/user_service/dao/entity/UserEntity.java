package by.taskManager.user_service.dao.entity;

import by.TaskManeger.utils.dto.UserRole;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    private String mail;
    private String password;
    private String fio;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;


    public UserEntity() {
    }

    public UserEntity(UserCreateDTO dto) {
        this.mail = dto.getMail();
        this.password = dto.getPassword();
        this.fio = dto.getFio();
        this.role = UserRole.valueOf(dto.getRole());
        this.status = UserStatus.valueOf(dto.getStatus());
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
