package com.ddungja.petmily.common.controller;


import com.ddungja.petmily.common.domain.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.REFRESH_TOKEN_NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> exceptionHandler(CustomException exception) {
        log.error("CustomException = {}", exception);
        return ResponseEntity.status(exception.getExceptionCode().getStatus()).body(exception.getExceptionCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException e) {
        log.error("ValidationException = {}", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<?> cookieException(MissingRequestCookieException e) {
        log.error("RefreshTokenCookieException = {}", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(REFRESH_TOKEN_NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> typeMisMatch(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException = {}", e);
        return ResponseEntity.badRequest().body("MethodArgumentTypeMismatchException");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> typeMisMatch(MissingServletRequestPartException e) {
        log.error("MissingServletRequestPartException = {}", e);
        return ResponseEntity.badRequest().body("MissingServletRequestPartException");
    }
}
