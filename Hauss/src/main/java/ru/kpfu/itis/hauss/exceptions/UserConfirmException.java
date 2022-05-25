package ru.kpfu.itis.hauss.exceptions;

public class UserConfirmException extends RuntimeException {

    public UserConfirmException(String message) {
        super(message);
    }

    public UserConfirmException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserConfirmException(Throwable cause) {
        super(cause);
    }
}
