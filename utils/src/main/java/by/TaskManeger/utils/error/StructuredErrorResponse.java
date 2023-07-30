package by.TaskManeger.utils.error;

import java.util.List;

public class StructuredErrorResponse{
    private String logref;
    private List<StructuredError> errors;

    public StructuredErrorResponse(List<StructuredError> errors) {
        this.logref = "strucrured_error";
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<StructuredError> getErrors() {
        return errors;
    }
}
