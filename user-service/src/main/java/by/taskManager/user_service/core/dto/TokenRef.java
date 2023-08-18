package by.taskManager.user_service.core.dto;

public class TokenRef {
    private String token;

    public TokenRef() {
    }

    public TokenRef(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
