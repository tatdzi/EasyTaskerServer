package by.TaskManeger.utils.dto;


import java.util.UUID;

public class TokenDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private UserRole role;

    public TokenDTO() {
    }

    public TokenDTO(UUID uuid, String mail, String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
    public UUID getUuid() {
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
