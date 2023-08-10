package com.ddungja.petmily.common.controller.response;

import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FieldErrorResponse {

    private final Map<String, String> response = new HashMap<>();

    public FieldErrorResponse(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            response.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
