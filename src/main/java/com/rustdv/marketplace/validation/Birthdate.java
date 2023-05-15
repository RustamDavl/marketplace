package com.rustdv.marketplace.validation;

import com.rustdv.marketplace.validation.validator.BirthdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthdateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Birthdate {

    String message() default "incorrect birthdate";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
