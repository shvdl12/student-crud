package com.lovely.sun.annotation.validator;

import com.lovely.sun.annotation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "Male".equals(value) || "Female".equals(value);
    }
}