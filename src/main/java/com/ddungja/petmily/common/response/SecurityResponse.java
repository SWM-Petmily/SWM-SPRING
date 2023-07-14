package com.ddungja.petmily.common.response;

import com.ddungja.petmily.common.domain.exception.ExceptionCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class SecurityResponse {

    public static void unAuthentication(HttpServletResponse response) throws IOException {
        String result = getResponse(ExceptionCode.UN_AUTHENTICATION);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(result);
    }

    public static void forbidden(HttpServletResponse response) throws IOException {
        String result = getResponse(ExceptionCode.FORBIDDEN);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().println(result);
    }
    

    private static String getResponse(ExceptionCode exceptionCode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(exceptionCode);
    }
}
