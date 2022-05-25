package ru.kpfu.itis.hauss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderNotFoundException extends GeneralNotFoundException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }
}
