package by.taskManager.user_service.core.dto;

import by.TaskManeger.utils.dto.UserRole;
import by.TaskManeger.utils.dto.UserStatus;
import by.taskManager.user_service.dao.entity.UserEntity;

public class UserCreateDTO {
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String mail, String fio, UserRole role, UserStatus status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }
    public UserCreateDTO(UserEntity entity) {
        this.mail = entity.getMail();
        this.fio = entity.getFio();
        this.role = entity.getRole();
        this.status = entity.getStatus();
        this.password = entity.getPassword();
    }


    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
