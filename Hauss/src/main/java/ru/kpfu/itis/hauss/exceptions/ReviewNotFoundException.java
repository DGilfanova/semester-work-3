package ru.kpfu.itis.hauss.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReviewNotFoundException(Throwable cause) {
        super(cause);
    }
}
