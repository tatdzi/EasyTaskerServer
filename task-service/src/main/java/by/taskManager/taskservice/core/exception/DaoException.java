package by.taskManager.taskservice.core.exception;

public class DaoException extends RuntimeException{
    private Error error;

    public DaoException(Error error) {
        this.error = error;
    }
}
