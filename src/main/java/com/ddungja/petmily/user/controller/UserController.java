package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.user.controller.response.CertificationResponse;
import com.ddungja.petmily.user.controller.response.UserLoginResponse;
import com.ddungja.petmily.user.domain.KakaoProfile;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
import com.ddungja.petmily.user.service.CoolSmsService;
import com.ddungja.petmily.user.service.KakaoService;
import com.ddungja.petmily.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.REFRESH_TOKEN_VALIDATION_FAILED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final KakaoService kakaoService;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final CoolSmsService coolSmsService;

    @Operation(summary = "카카오 로그인")
    @ApiResponse(responseCode = "200", description = "카카오 로그인 성공", content = @Content(schema = @Schema(implementation = UserLoginResponse.class)))
    @GetMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code") String code) throws URISyntaxException {
        log.debug("카카오 로그인 code = {}", code);
        KakaoProfile kakaoProfile = kakaoService.getInfo(code);
        User user = userService.login(kakaoProfile);
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        return ResponseEntity.ok().body(UserLoginResponse.from(user, accessToken, refreshToken));
    }

    @Operation(summary = "휴대전화 인증번호 발송")
    @ApiResponse(responseCode = "200", description = "휴대전화 인증번호 발송 성공", content = @Content(schema = @Schema(implementation = CertificationResponse.class)))
    @PostMapping("/certification")
    public ResponseEntity<?> sendCertificationNumber(String phoneNumber) {
        return ResponseEntity.ok().body(CertificationResponse.from(coolSmsService.sendCertificationNumber(phoneNumber)));
    }


    @Operation(summary = "닉네임 수정")
    @PutMapping
    public ResponseEntity<?> modify(@AuthenticationPrincipal User user, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.modifyNickname(user.getId(), userUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "권한테스트")
    @GetMapping("/authorization")
    public ResponseEntity<?> authorizationTest() {
        log.debug("권한 테스트");
        return ResponseEntity.ok("토큰이 존재합니다");
    }

    @Operation(summary = "테스트용 엑세스토큰 반환 userId = 1")
    @GetMapping("/test/token/1")
    public ResponseEntity<?> testAccessToken1() {
        String testAccessToken = jwtProvider.createTestAccessToken(1L);
        return ResponseEntity.ok(testAccessToken);
    }

    @Operation(summary = "테스트용 엑세스토큰 반환 userId = 2")
    @GetMapping("/test/token/2")
    public ResponseEntity<?> testAccessToken2() {
        String testAccessToken = jwtProvider.createTestAccessToken(2L);
        return ResponseEntity.ok(testAccessToken);
    }

    @Operation(summary = "로컬에서 카카오 로그인 테스트")
    @GetMapping("/kakao/login/test")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://kauth.kakao.com/oauth/authorize?client_id=ee34f16978b76a36b7c087376c6bbef2&redirect_uri=http://localhost:8080/users/kakao&response_type=code");
    }

    @Operation(summary = "엑세스 토큰/리프레시 토큰 재발급")
    @ApiResponse(responseCode = "200", description = "엑세스 토큰/리프레시 토큰 재발급 성공", content = @Content(schema = @Schema(implementation = TokenRefreshResponse.class)))
    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(@CookieValue(value = "refreshToken") Cookie cookie) {
        log.debug("refresh 요청 = {}", cookie.getValue());
        String refreshToken = cookie.getValue();
        log.debug("refreshToken = {}", refreshToken);
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(user);
            refreshToken = jwtProvider.createRefreshToken(user);
            return ResponseEntity.ok().body(TokenRefreshResponse.from(accessToken, refreshToken));
        }
        throw new CustomException(REFRESH_TOKEN_VALIDATION_FAILED);
    }


}
