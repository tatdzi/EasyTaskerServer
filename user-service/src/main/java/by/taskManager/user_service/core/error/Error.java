package by.taskManager.user_service.core.error;

public class Error {
    private String logref;
    private String message;


    public Error(String message, String logref) {
        this.message = message;
        this.logref = logref;
    }

    public String getMessage() {
        return message;
    }

    public String getLogref() {
        return logref;
    }
}
