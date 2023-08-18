package by.taskManager.taskservice.core.exception;

import by.TaskManeger.utils.error.Error;

import java.util.List;

public class ErrorResponseException extends RuntimeException{
    private List<Error> errors;

    public ErrorResponseException(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
