package by.taskManager.user_service.core.error;

import java.util.ArrayList;
import java.util.List;

public class StrcturedErrorException extends RuntimeException{
    private String logref;
    private List<StructuredError> errors;

    public StrcturedErrorException() {
        this.logref = "structured_error";
        this.errors = new ArrayList<>();
    }

    public String getLogref() {
        return logref;
    }

    public List<StructuredError> getErrors() {
        return errors;
    }

    public void setError(StructuredError error) {
        errors.add(error);
    }
}
