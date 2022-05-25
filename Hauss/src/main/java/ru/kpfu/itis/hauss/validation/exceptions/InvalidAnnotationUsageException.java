package ru.kpfu.itis.hauss.validation.exceptions;

public class InvalidAnnotationUsageException extends RuntimeException {

    public InvalidAnnotationUsageException(String message) {
        super(message);
    }

    public InvalidAnnotationUsageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAnnotationUsageException(Throwable cause) {
        super(cause);
    }
}
