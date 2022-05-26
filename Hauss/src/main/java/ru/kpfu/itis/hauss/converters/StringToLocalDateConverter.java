package ru.kpfu.itis.hauss.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.ORDER_DATE_TEMPLATE;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(ORDER_DATE_TEMPLATE));
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}

