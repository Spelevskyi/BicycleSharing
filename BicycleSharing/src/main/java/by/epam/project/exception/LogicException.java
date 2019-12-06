package by.epam.project.exception;

public class LogicException extends Exception {

    public LogicException() {
        super();
    }

    public LogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(Throwable arg0) {
        super(arg0);
    }

}
