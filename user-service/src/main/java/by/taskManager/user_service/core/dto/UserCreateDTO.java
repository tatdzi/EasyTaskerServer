package by.taskManager.user_service.core.dto;

public class UserCreateDTO {
    private String mail;
    private String fio;
    private String role;
    private String status;
    private String password;
    private String code;

    public UserCreateDTO(String mail, String fio, String role, String status, String password,String code) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
