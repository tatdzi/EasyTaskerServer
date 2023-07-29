package by.taskManager.auditservice.core.dto;



public class UserDTO {
    private String uuid;
    private String mail;
    private String fio;
    private String role;

    public UserDTO(String uuid, String mail, String fio, String role) {
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

    public String getRole() {
        return role;
    }


}
