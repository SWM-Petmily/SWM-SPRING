package com.ddungja.petmily.common.controller;


import com.ddungja.petmily.common.controller.response.FieldErrorResponse;
import com.ddungja.petmily.common.exception.CustomException;
import io.sentry.Sentry;
import io.sentry.spring.jakarta.tracing.SentrySpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ddungja.petmily.common.exception.ExceptionCode.REFRESH_TOKEN_NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @SentrySpan
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> exceptionHandler(CustomException exception) {
        Sentry.captureException(exception);
        log.warn("CustomException = {}", exception);
        return ResponseEntity.status(exception.getExceptionCode().getStatus()).body(exception.getExceptionCode());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validation(MethodArgumentNotValidException exception) {
        Sentry.captureException(exception);
        log.warn("MethodArgumentNotValidException = {}", exception);
        FieldErrorResponse fieldValidation = new FieldErrorResponse(exception);
        return ResponseEntity.badRequest().body(fieldValidation.getResponse());
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<Object> cookieException(MissingRequestCookieException exception) {
        Sentry.captureException(exception);
        log.error("MissingRequestCookieException = {}", exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(REFRESH_TOKEN_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception exception) {
        Sentry.captureException(exception);
        log.error("Exception = {}", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
