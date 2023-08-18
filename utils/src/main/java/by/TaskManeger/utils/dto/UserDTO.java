package by.TaskManeger.utils.dto;


import by.TaskManeger.utils.serializator.LocalDateTimeSerializer;
import by.TaskManeger.utils.serializator.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID uuid;

    @JsonProperty("dt_create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private LocalDateTime dtCreate;

    @JsonProperty("dt_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private LocalDateTime dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, LocalDateTime dt_update, LocalDateTime dt_create, String mail, String fio, UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dt_create;
        this.dtUpdate = dt_update;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }
    public LocalDateTime getDt_create() {
        return dtCreate;
    }
    public LocalDateTime getDt_update() {
        return dtUpdate;
    }
    public String getMail() {
        return mail;
    }
    public String getFio() {
        return fio;
    }
    public UserRole getRole() {
        return role;
    }
    public UserStatus getStatus() {
        return status;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDtCreate(LocalDateTime dt_create) {
        this.dtCreate = dt_create;
    }

    public void setDtUpdate(LocalDateTime dt_update) {
        this.dtUpdate = dt_update;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
