package by.taskManager.user_service.core.dto;

import by.taskManager.user_service.dao.entity.UserEntity;

public class UserCreateDTO {
    private String mail;
    private String fio;
    private String role;
    private String status;
    private String password;

    public UserCreateDTO(String mail, String fio, String role, String status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }
    public UserCreateDTO(UserEntity entity) {
        this.mail = entity.getMail();
        this.fio = entity.getFio();
        this.role = entity.getRole().toString();
        this.status = entity.getStatus().toString();
        this.password = entity.getPassword();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPassword() {
        return password;
    }
}
