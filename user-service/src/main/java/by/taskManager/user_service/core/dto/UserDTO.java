package by.taskManager.user_service.core.dto;

import by.taskManager.user_service.core.serializator.LocalDateTimeSerializer;
import by.taskManager.user_service.dao.entity.UserEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class UserDTO {
    private String uuid;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;
    private String mail;
    private String fio;
    private String role;
    private String status;

    public UserDTO(String uuid, String mail, String fio, String role, String status) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public UserDTO(UserEntity entity) {
        this.uuid = entity.getUuid().toString();
        this.dtCreate = entity.getDtCreate();
        this.dtUpdate = entity.getDtUpdate();
        this.mail = entity.getMail();
        this.fio = entity.getFio();
        this.role = entity.getRole().toString();
        this.status = entity.getStatus().toString();
    }

    public String getUuid() {
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

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}
