package com.youcode.sudest_market.validator;

import com.youcode.sudest_market.annotation.UniqueField;
import com.youcode.sudest_market.util.ApplicationContextProvider;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    private String fieldName;
    private Object repository;
    private Method repositoryMethod;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        Class<?> repositoryClass = constraintAnnotation.repository();

        // Retrieve repository bean from Spring context
        this.repository = ApplicationContextProvider.getApplicationContext().getBean(repositoryClass);

        try {
            this.repositoryMethod = findRepositoryMethod(repositoryClass, fieldName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No suitable method found in repository for field: " + fieldName, e);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // Empty values are not checked for uniqueness
        }

        try {
            Object result = repositoryMethod.invoke(repository, value);
            if (result instanceof Optional<?>) {
                return ((Optional<?>) result).isEmpty();
            }
            return false; // If repository method doesn't return Optional, treat as invalid
        } catch (Exception e) {
            throw new RuntimeException("Error while validating uniqueness for field: " + fieldName, e);
        }
    }

    private Method findRepositoryMethod(Class<?> repositoryClass, String fieldName) throws NoSuchMethodException {
        String methodName = "findBy" + capitalize(fieldName);

        for (Method method : repositoryClass.getMethods()) {
            if (method.getName().equals(methodName)
                    && method.getParameterCount() == 1
                    && method.getParameterTypes()[0] == String.class
                    && Optional.class.isAssignableFrom(method.getReturnType())) {
                return method;
            }
        }
        throw new NoSuchMethodException("No suitable method named " + methodName + " found in " + repositoryClass.getName());
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
