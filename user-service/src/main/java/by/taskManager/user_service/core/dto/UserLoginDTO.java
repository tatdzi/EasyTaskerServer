package by.taskManager.user_service.core.dto;

public class UserLoginDTO {
    private String mail;
    private String password;

    public UserLoginDTO(String mail, String password) {
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
