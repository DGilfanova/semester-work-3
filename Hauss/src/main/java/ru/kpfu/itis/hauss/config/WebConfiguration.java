package ru.kpfu.itis.hauss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.hauss.converters.StringToLocalDateConverter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public StringToLocalDateConverter stringToDateConverter() {
        return new StringToLocalDateConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToDateConverter());
    }
}

