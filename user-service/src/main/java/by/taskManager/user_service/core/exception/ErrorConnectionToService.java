package by.taskManager.user_service.core.exception;

public class ErrorConnectionToService extends RuntimeException{
    public ErrorConnectionToService(String message) {
        super(message);
    }
}
