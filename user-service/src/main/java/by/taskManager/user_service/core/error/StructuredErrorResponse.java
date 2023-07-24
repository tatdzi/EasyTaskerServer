package by.taskManager.user_service.core.error;

import java.util.List;

public class StructuredErrorResponse{
    private String logref;
    private List<StructuredError> errors;

    public StructuredErrorResponse(String logref, List<StructuredError> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<StructuredError> getErrors() {
        return errors;
    }
}
