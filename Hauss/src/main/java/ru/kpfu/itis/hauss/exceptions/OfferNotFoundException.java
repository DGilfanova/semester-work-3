package ru.kpfu.itis.hauss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OfferNotFoundException extends GeneralNotFoundException {

    public OfferNotFoundException(String message) {
        super(message);
    }

    public OfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferNotFoundException(Throwable cause) {
        super(cause);
    }
}
