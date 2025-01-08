package com.youcode.sudest_market.validator;

import com.youcode.sudest_market.annotation.UniqueField;
import com.youcode.sudest_market.repository.AppUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {
    @Autowired
    AppUserRepository userRepository;

    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (fieldName.equals("username")) {
            return userRepository.findByUsername(value).isEmpty();
        } else if (fieldName.equals("email")) {
            return userRepository.findByEmail(value).isEmpty();
        }
        return true;
    }
}