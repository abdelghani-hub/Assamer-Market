package com.youcode.sudest_market.annotation;

import com.youcode.sudest_market.validator.UniqueFieldValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFieldValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {

    String message() default "already exists";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
    String fieldName();
}