package ru.kpfu.itis.hauss.utils.api.currencies;

public class CurrencyConverterException extends RuntimeException {

    public CurrencyConverterException(String message) {
        super(message);
    }

    public CurrencyConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyConverterException(Throwable cause) {
        super(cause);
    }
}
