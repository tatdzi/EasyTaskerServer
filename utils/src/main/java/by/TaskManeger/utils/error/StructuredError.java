package by.TaskManeger.utils.error;

public class StructuredError{
    private String field;
    private String message;

    public StructuredError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
