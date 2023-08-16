package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "Test API")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final JwtProvider jwtProvider;
    private final UserService userService;
    @Operation(summary = "테스트용 엑세스토큰 반환 userId = 1")
    @GetMapping("/test/token/1")
    public ResponseEntity<String> testAccessToken1() {
        String testAccessToken = jwtProvider.createTestAccessToken(1L);
        return ResponseEntity.ok(testAccessToken);
    }

    @Operation(summary = "테스트용 엑세스토큰 반환 userId = 2")
    @GetMapping("/test/token/2")
    public ResponseEntity<String> testAccessToken2() {
        String testAccessToken = jwtProvider.createTestAccessToken(2L);
        return ResponseEntity.ok(testAccessToken);
    }

    @Operation(summary = "로컬에서 카카오 로그인 테스트")
    @GetMapping("/kakao/login/test")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://kauth.kakao.com/oauth/authorize?client_id=ee34f16978b76a36b7c087376c6bbef2&redirect_uri=http://localhost:8080/users/kakao&response_type=code");
    }

    @Operation(summary = "권한테스트")
    @GetMapping("/authorization")
    public ResponseEntity<String> authorizationTest() {
        return ResponseEntity.ok("엑세스 토큰이 존재합니다");
    }


    @Operation(summary = "유저 인증 초기화, 인증번호 기록 삭제")
    @GetMapping("/reset")
    public ResponseEntity<Void> tes2() {
        userService.reset();
        return ResponseEntity.noContent().build();
    }
}
