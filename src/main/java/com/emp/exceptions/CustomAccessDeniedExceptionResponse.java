package com.emp.exceptions;

public class CustomAccessDeniedExceptionResponse {

    private String message;

    public CustomAccessDeniedExceptionResponse() {
        this.message = "Unauthorized access.You do not have permission to access.";
    }
}
