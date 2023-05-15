package com.rustdv.marketplace.validation.validator;

import com.rustdv.marketplace.validation.Birthdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdateValidator implements ConstraintValidator<Birthdate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
    }
}
