package by.taskManager.user_service.core.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse extends RuntimeException {
    private List<Error> errors;

    public ErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setError(Error error) {
        errors.add(error);
    }
}
