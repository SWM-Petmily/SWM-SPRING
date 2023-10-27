package com.ddungja.petmily.common.controller;


import com.amazonaws.AmazonServiceException;
import com.ddungja.petmily.common.controller.response.FieldErrorResponse;
import com.ddungja.petmily.common.exception.CustomException;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.sentry.Sentry;
import io.sentry.spring.jakarta.tracing.SentrySpan;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static com.ddungja.petmily.common.exception.ExceptionCode.REFRESH_TOKEN_NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @SentrySpan
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> exceptionHandler(CustomException exception) {
        Sentry.captureException(exception);
        log.warn("CustomException = {}", exception);
        return ResponseEntity.status(exception.getExceptionCode().getStatus()).body(exception.getExceptionCode());
    }

    @ExceptionHandler(NurigoUnknownException.class)
    public ResponseEntity<Object> exceptionHandler(NurigoUnknownException exception) {
        Sentry.captureException(exception);
        log.error("NurigoUnknownException = {}", exception);
        return ResponseEntity.badRequest().body(exception.getMessage());
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        Sentry.captureException(exception);
        log.error("MethodArgumentTypeMismatchException = {}", exception);
        return ResponseEntity.badRequest().body("MethodArgumentTypeMismatchException");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> missingServletRequestPartException(MissingServletRequestPartException exception) {
        Sentry.captureException(exception);
        log.error("MissingServletRequestPartException = {}", exception);
        return ResponseEntity.badRequest().body("MissingServletRequestPartException");
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<Object> amazonServiceException(AmazonServiceException exception) {
        Sentry.captureException(exception);
        log.error("AmazonServiceException = {}", exception);
        return ResponseEntity.badRequest().body("AmazonServiceException");
    }

    @ExceptionHandler(FirebaseMessagingException.class)
    public ResponseEntity<Object> fcmException(FirebaseMessagingException exception) {
        Sentry.captureException(exception);
        log.error("fcmException = {}", exception);
        return ResponseEntity.badRequest().body("FirebaseMessagingException");
    }
}
