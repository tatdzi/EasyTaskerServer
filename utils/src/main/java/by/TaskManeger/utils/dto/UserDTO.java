package by.TaskManeger.utils.dto;


import by.TaskManeger.utils.serializator.LocalDateTimeSerializer;
import by.TaskManeger.utils.serializator.StringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class UserDTO {
    private String uuid;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private LocalDateTime dt_create;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private LocalDateTime dt_update;
    private String mail;
    private String fio;
    private String role;
    private String status;

    public UserDTO() {
    }

    public UserDTO(String uuid, LocalDateTime dt_update, LocalDateTime dt_create, String mail, String fio, String role, String status) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }
    public LocalDateTime getDt_create() {
        return dt_create;
    }
    public LocalDateTime getDt_update() {
        return dt_update;
    }
    public String getMail() {
        return mail;
    }
    public String getFio() {
        return fio;
    }
    public String getRole() {
        return role;
    }
    public String getStatus() {
        return status;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public void setDt_update(LocalDateTime dt_update) {
        this.dt_update = dt_update;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
