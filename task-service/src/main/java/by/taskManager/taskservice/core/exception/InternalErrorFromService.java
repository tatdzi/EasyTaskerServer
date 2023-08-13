package by.taskManager.taskservice.core.exception;

public class InternalErrorFromService extends RuntimeException{

    public InternalErrorFromService(String message) {
        super(message);
    }
}
