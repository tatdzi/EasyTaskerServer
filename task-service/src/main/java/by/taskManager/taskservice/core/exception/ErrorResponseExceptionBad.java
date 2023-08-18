package by.taskManager.taskservice.core.exception;

import by.TaskManeger.utils.error.Error;

import java.util.List;

public class ErrorResponseExceptionBad extends RuntimeException{
    private List<Error> errors;

    public ErrorResponseExceptionBad(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
