package ru.kpfu.itis.hauss.validation.validators;

import org.springframework.beans.BeanWrapperImpl;
import ru.kpfu.itis.hauss.validation.annotations.FieldMatch;
import ru.kpfu.itis.hauss.validation.exceptions.InvalidAnnotationUsageException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String message;
    private String firstField;
    private String secondField;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        message = constraintAnnotation.message();
        firstField = constraintAnnotation.firstField();
        secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        final BeanWrapperImpl bean = new BeanWrapperImpl(value);

        final Object firstObject = bean.getPropertyValue(firstField);
        final Object secondObject = bean.getPropertyValue(secondField);

        boolean result = false;

        if (firstObject == null || secondObject == null) {
            throw new InvalidAnnotationUsageException("Annotation should match TWO field");
        }

        if (firstObject.equals(secondObject)) {
            result = true;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        return result;
    }
}
