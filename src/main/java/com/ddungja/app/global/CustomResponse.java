package com.ddungja.app.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomResponse {

    //로그인 안함
    public static void unAuthentication(HttpServletResponse response, String msg) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
        String responseBody = objectMapper.writeValueAsString(responseDto);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(401);
        response.getWriter().println(responseBody);
    }


    // 로그인 성공했을 때
    public static void success(HttpServletResponse response, Object dto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<?> responseDto = new ResponseDto<>(1, "로그인 성공", dto);
        String responseBody = objectMapper.writeValueAsString(responseDto);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        response.getWriter().println(responseBody);
    }

    //권한없는 페이지에 들어갈때
    public static void forbidden(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<?> responseDto = new ResponseDto<>(-1, "권한이 없습니다.", null);
        String responseBody = objectMapper.writeValueAsString(responseDto);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        response.getWriter().println(responseBody);
    }
}
