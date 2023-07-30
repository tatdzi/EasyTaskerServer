package by.TaskManeger.utils.error;

public class Error {
    private String logref;
    private String message;


    public Error(String message) {
        this.message = message;
        this.logref = "error";
    }

    public String getMessage() {
        return message;
    }

    public String getLogref() {
        return logref;
    }
}
