package ru.kpfu.itis.hauss.helpers.processors;

public class OrderDateException extends RuntimeException {

    public OrderDateException(String message) {
        super(message);
    }

    public OrderDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDateException(Throwable cause) {
        super(cause);
    }
}
