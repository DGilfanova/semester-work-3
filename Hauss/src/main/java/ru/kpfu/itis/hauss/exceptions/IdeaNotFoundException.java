package ru.kpfu.itis.hauss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IdeaNotFoundException extends GeneralNotFoundException {

    public IdeaNotFoundException(String message) {
        super(message);
    }

    public IdeaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdeaNotFoundException(Throwable cause) {
        super(cause);
    }
}
