package com.lovely.sun.annotation;

import com.lovely.sun.annotation.validator.GenderValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface Gender {
    String message() default "Gender should only be Male or Female";

    Class[] groups() default {};

    Class[] payload() default {};
}

