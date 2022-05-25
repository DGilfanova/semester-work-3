package ru.kpfu.itis.hauss.exceptions;

public class VkOAuthException extends RuntimeException{

    public VkOAuthException(String message) {
        super(message);
    }

    public VkOAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public VkOAuthException(Throwable cause) {
        super(cause);
    }
}
