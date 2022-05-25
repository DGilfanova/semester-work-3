package ru.kpfu.itis.hauss.helpers.processors;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;

@Component
public class OrderDateProcessor {

    public void processDate(LocalDate date) {
        if (date.isBefore(LocalDate.now().plusDays(1))) {
            throw new OrderDateException("You can order the offer no earlier than tomorrow");
        }

        if (date.isAfter(LocalDate.now().plusYears(1))) {
            throw new OrderDateException("You can order the offer no later than in a year");
        }
    }
}
