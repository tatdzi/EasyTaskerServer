package by.taskManager.taskservice.core.exception;

import by.TaskManeger.utils.error.StructuredError;

import java.util.ArrayList;
import java.util.List;

public class StrcturedErrorException extends RuntimeException{
    private List<StructuredError> errors;

    public StrcturedErrorException() {
        this.errors = new ArrayList<>();
    }

    public StrcturedErrorException(List<StructuredError> errors) {
        this.errors = errors;
    }

    public List<StructuredError> getErrors() {
        return errors;
    }

    public void setError(StructuredError error) {
        errors.add(error);
    }
}
