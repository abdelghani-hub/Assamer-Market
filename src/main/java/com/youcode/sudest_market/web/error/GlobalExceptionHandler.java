package com.youcode.sudest_market.web.error;

import com.youcode.sudest_market.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<Map<String, String>> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("field", error.getField());
            errorDetail.put("message", error.getDefaultMessage());
            errorDetail.put("code", "VALIDATION_ERROR");
            errors.add(errorDetail);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistException(AlreadyExistException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("field", exception.getField());
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "ALREADY_EXISTS");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "ENTITY_NOT_FOUND");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMismatchException(MismatchException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("field", exception.getField());
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "MISMATCH_ERROR");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("field", "credentials");
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "INVALID_CREDENTIALS");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullOrBlankArgException.class)
    public ResponseEntity<Map<String, Object>> handleNullOrBlankArgException(NullOrBlankArgException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("arg", exception.getArg());
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "NULL_OR_BLANK_ARG");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidConstraintException.class)
    public ResponseEntity<Map<String, Object>> handleNotValidConstraintException(NotValidConstraintException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "NOT_VALID_CONSTRAINT");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, Object>> handleDateTimeParseException(DateTimeParseException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("field", "date");
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "INVALID_DATE_FORMAT");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, Object>> handleExpiredJwtException(ExpiredJwtException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("field", "token");
        errorDetail.put("message", exception.getMessage());
        errorDetail.put("code", "EXPIRED_TOKEN");
        errors.add(errorDetail);

        response.put("status", "error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
