package by.taskManager.user_service.core.dto;

import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.UserService;

public class TokenDTO {
    private String mail;
    private String role;

    public TokenDTO(String mail, String role) {
        this.mail = mail;
        this.role = role;
    }
    public TokenDTO(UserEntity entity) {
        this.mail = entity.getMail();
        this.role = entity.getRole().name();
    }


    public String getMail() {
        return mail;
    }

    public String getRole() {
        return role;
    }
}
