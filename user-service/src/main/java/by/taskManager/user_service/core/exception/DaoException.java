package by.taskManager.user_service.core.exception;

public class DaoException extends RuntimeException{
    private Error error;

    public DaoException(Error error) {
        this.error = error;
    }
}
