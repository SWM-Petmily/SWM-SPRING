package com.ddungja.app.common.controller;


import com.ddungja.app.common.domain.exception.CustomException;
import com.ddungja.app.common.domain.exception.ExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ddungja.app.common.domain.exception.ExceptionCode.REFRESH_TOKEN_NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> exceptionHandler(CustomException exception) {
        return ResponseEntity.status(exception.getExceptionCode().getStatus()).body(exception.getExceptionCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<?> cookieException(MissingRequestCookieException e) {
        return ResponseEntity.badRequest().body(REFRESH_TOKEN_NOT_FOUND);
    }
}
