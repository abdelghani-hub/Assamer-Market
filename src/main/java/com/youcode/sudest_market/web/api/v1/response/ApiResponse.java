package com.youcode.sudest_market.web.api.v1.response;

import java.util.List;

public class ApiResponse<T> {
    private T data;
    private String message;
    private String status;
    private List<ErrorDetail> errors;

    // Constructor
    public ApiResponse(T data, String message, String status, List<ErrorDetail> errors) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    // Static success method
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, "success", null);
    }

    // Static error method
    public static <T> ApiResponse<T> error(String message, List<ErrorDetail> errors) {
        return new ApiResponse<>(null, message, "error", errors);
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    // ErrorDetail inner class
    public static class ErrorDetail {
        private String field;
        private String message;

        private String code;

        public ErrorDetail(String field, String message, String code) {
            this.field = field;
            this.message = message;
            this.code = code;
        }

        // Getters and Setters
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}