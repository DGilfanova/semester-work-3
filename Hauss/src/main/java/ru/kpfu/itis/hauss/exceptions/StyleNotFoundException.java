package ru.kpfu.itis.hauss.exceptions;

public class StyleNotFoundException extends GeneralNotFoundException {

    public StyleNotFoundException(String message) {
        super(message);
    }

    public StyleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StyleNotFoundException(Throwable cause) {
        super(cause);
    }
}
