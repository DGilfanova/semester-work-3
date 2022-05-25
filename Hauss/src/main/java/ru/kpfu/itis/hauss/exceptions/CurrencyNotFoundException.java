package ru.kpfu.itis.hauss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CurrencyNotFoundException extends GeneralNotFoundException {

    public CurrencyNotFoundException(String message) {
        super(message);
    }

    public CurrencyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyNotFoundException(Throwable cause) {
        super(cause);
    }
}
