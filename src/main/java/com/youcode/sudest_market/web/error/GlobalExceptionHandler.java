package com.youcode.sudest_market.web.error;

import com.youcode.sudest_market.exception.*;
import com.youcode.sudest_market.web.api.v1.response.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<ApiResponse.ErrorDetail> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiResponse.ErrorDetail(error.getField(), error.getDefaultMessage(), "VALIDATION_ERROR"))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Validation failed", errors));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistException(AlreadyExistException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail(exception.getField(), exception.getMessage(), "ALREADY_EXISTS");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error("Resource already exists", List.of(errorDetail)));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail(null, exception.getMessage(), "ENTITY_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Resource not found", List.of(errorDetail)));
    }

    @ExceptionHandler(MismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMismatchException(MismatchException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail(exception.getField(), exception.getMessage(), "MISMATCH_ERROR");
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Mismatch error", List.of(errorDetail)));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail("credentials", exception.getMessage(), "INVALID_CREDENTIALS");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Invalid credentials", List.of(errorDetail)));
    }

    @ExceptionHandler(NullOrBlankArgException.class)
    public ResponseEntity<ApiResponse<Void>> handleNullOrBlankArgException(NullOrBlankArgException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail(exception.getArg(), exception.getMessage(), "NULL_OR_BLANK_ARG");
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Null or blank argument", List.of(errorDetail)));
    }

    @ExceptionHandler(NotValidConstraintException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotValidConstraintException(NotValidConstraintException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail(null, exception.getMessage(), "NOT_VALID_CONSTRAINT");
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Invalid constraint", List.of(errorDetail)));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiResponse<Void>> handleDateTimeParseException(DateTimeParseException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail("date", exception.getMessage(), "INVALID_DATE_FORMAT");
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Invalid date format", List.of(errorDetail)));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<Void>> handleExpiredJwtException(ExpiredJwtException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail("token", exception.getMessage(), "EXPIRED_TOKEN");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Expired token", List.of(errorDetail)));
    }

    @ExceptionHandler(SomethingWrongException.class)
    public ResponseEntity<ApiResponse<Void>> handleSomethingWrongException(SomethingWrongException exception) {
        ApiResponse.ErrorDetail errorDetail = new ApiResponse.ErrorDetail(null, exception.getMessage(), "SOMETHING_WRONG");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("INTERNAL_SERVER_ERROR", List.of(errorDetail)));
    }
}