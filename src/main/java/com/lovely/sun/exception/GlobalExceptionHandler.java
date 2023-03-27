package com.lovely.sun.exception;

import com.lovely.sun.model.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    //유효성 검사 실패 에러 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleValidationError(MethodArgumentNotValidException e) {
        return ResponseEntity.ok(CommonResponse.builder()
                .code(ErrorCode.INVALID_REQUEST_PARAMS.getCode())
                .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build());
    }

    //미디어타입 에러 처리
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<CommonResponse> handleMediaTypeError(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                CommonResponse.builder().
                        code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .message(e.getMessage()).build()
        );
    }

    //메소드 에러 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse> handleMethodError(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                CommonResponse.builder().
                        code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                        .message(e.getMessage()).build()
        );
    }

    //발송 전문 에러 처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse> handleBadRequestError(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CommonResponse.builder().
                        code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .message(e.getMessage()).build()
        );
    }

    /*
    //런타임 에러 처리
    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeError(RestException e) {
        return ResponseEntity.ok(ErrorResponse.builder()
                .code(e.getError().getCode())
                .message(e.getError().getMessage())
                .build());
    }*/


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonResponse> ResourceNotFoundException(Exception e) {
        return ResponseEntity.ok(CommonResponse.builder()
                .code(ErrorCode.STUDENT_NOT_FOUND.getCode())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonResponse> handleConstraintViolationError(Exception e) {
        return ResponseEntity.ok(CommonResponse.builder()
                .code(ErrorCode.DUPIICATE_KEY_ERROR.getCode())
                .message(e.getMessage())
                .build());
    }

    //그 외 서버 에러 처리(DB, Redis 연결 실패 등)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleCommonError(Exception e) {
        log.error("{}", e);

        return ResponseEntity.ok(CommonResponse.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build());
    }
}
