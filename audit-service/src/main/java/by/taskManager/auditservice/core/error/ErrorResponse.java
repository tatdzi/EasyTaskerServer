package by.taskManager.auditservice.core.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<java.lang.Error> errors;

    public ErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public void setError(java.lang.Error error) {
        errors.add(error);
    }
}
