package by.TaskManeger.utils.dto;


public class TokenDTO {
    private String mail;
    private String role;

    public TokenDTO(String mail, String role) {
        this.mail = mail;
        this.role = role;
    }


    public String getMail() {
        return mail;
    }

    public String getRole() {
        return role;
    }
}
