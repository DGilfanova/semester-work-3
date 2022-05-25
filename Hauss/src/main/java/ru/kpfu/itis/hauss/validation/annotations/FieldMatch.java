package ru.kpfu.itis.hauss.validation.annotations;

import ru.kpfu.itis.hauss.validation.validators.FieldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
    String message() default "Password is not confirmed";

    String firstField();
    String secondField();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
