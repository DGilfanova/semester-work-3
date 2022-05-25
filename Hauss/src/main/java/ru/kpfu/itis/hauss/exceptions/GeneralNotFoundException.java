package ru.kpfu.itis.hauss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GeneralNotFoundException extends RuntimeException{

    public GeneralNotFoundException(String message) {
        super(message);
    }

    public GeneralNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralNotFoundException(Throwable cause) {
        super(cause);
    }
}
