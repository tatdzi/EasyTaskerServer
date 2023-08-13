package by.taskManager.taskservice.core.exception;

public class ErrorConnectionToService extends RuntimeException{
    public ErrorConnectionToService(String message) {
        super(message);
    }
}
