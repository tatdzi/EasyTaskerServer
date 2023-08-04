package by.taskManager.auditservice.core.exception;




import by.TaskManeger.utils.error.StructuredError;

import java.util.ArrayList;
import java.util.List;

public class StrcturedErrorException extends RuntimeException{
    private List<StructuredError> errors;

    public StrcturedErrorException() {
        this.errors = new ArrayList<>();
    }

    public List<StructuredError> getErrors() {
        return errors;
    }

    public void setError(StructuredError error) {
        errors.add(error);
    }
}
