package by.TaskManeger.utils.dto;


public class TokenDTO {
    private String uuid;
    private String mail;
    private String fio;
    private UserRole role;


    public TokenDTO(String uuid, String mail, String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public String getUuid() {
        return uuid;
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
}
