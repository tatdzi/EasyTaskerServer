package by.TaskManeger.utils.dto;

public class ServiceResponse {
    private int status;
    private Object body;

    public ServiceResponse() {
    }

    public ServiceResponse(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public Object getBody() {
        return body;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
