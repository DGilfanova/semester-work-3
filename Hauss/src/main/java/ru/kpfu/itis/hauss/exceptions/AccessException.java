package ru.kpfu.itis.hauss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessException extends RuntimeException {

    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessException(Throwable cause) {
        super(cause);
    }
}
