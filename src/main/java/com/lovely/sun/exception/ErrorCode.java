package com.lovely.sun.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    SUCCESS(HttpStatus.OK, "0000", "success"),
    INVALID_REQUEST_PARAMS(HttpStatus.OK, "1001", "Invalid parameter"),
    STUDENT_NOT_FOUND(HttpStatus.OK, "1014", "Student not found"),
    DUPIICATE_KEY_ERROR(HttpStatus.OK, "1015", "Duplicate key error"),
    INTERNAL_SERVER_ERROR(HttpStatus.OK, "9999", "Server error");

    private final HttpStatus status;
    private final String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

