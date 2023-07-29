package by.taskManager.user_service.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDTO {
    private String mail;
    private String password;
    @JsonCreator
    public LoginDTO(@JsonProperty String mail,
                    @JsonProperty String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
