package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.user.controller.response.UserSignUpResponse;
import com.ddungja.petmily.user.controller.response.UserCertificationResponse;
import com.ddungja.petmily.user.domain.KakaoProfile;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
import com.ddungja.petmily.user.service.KakaoService;
import com.ddungja.petmily.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.REFRESH_TOKEN_VALIDATION_FAILED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final KakaoService kakaoService;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Operation(summary = "카카오 로그인/회원가입")
    @GetMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code") String code) throws URISyntaxException {
        log.debug("카카오 로그인/회원가입 code = {}", code);
        KakaoProfile kakaoProfile = kakaoService.getInfo(code);
        User user = userService.login(kakaoProfile);
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        String refreshTokenCookie = createRefreshTokenCookie(refreshToken);
        return ResponseEntity.ok().header(SET_COOKIE, refreshTokenCookie).header(AUTHORIZATION, accessToken).body(UserCertificationResponse.from(user));
    }

    private static String createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(Duration.ofDays(30)) // 쿠키의 유효 기간 설정
                .path("/") // 쿠키의 경로를 지정하여 모든 경로에서 접근 가능하도록 함
                .secure(true) // HTTPS에서만 쿠키 전송 허용 (SSL/TLS 암호화된 연결)
                .sameSite("Lax") // CSRF 공격 방지를 위해 SameSite 속성 설정 (Lax로 설정)
                .domain(".petmily.site") // 도메인 설정 (하위 도메인 포함하여 적용)
                .httpOnly(true) // 브라우저에서 쿠키에 직접 접근 불가하도록 제한
                .build().toString();
    }

    @Operation(summary = "엑세스 토큰 재발급")
    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(@CookieValue(value = "refreshToken") Cookie cookie) {
        log.debug("refresh 요청 = {}", cookie.getValue());
        String refreshToken = cookie.getValue();
        log.debug("refreshToken = {}", refreshToken);
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(user);
            refreshToken = jwtProvider.createRefreshToken(user);
            String refreshTokenCookie = createRefreshTokenCookie(refreshToken);
            return ResponseEntity.ok().header(SET_COOKIE, refreshTokenCookie).header(AUTHORIZATION, accessToken).build();
        }
        throw new CustomException(REFRESH_TOKEN_VALIDATION_FAILED);
    }

    @Operation(summary = "카카오 회원가입 후 추가적인 정보와 휴대전화 인증")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@AuthenticationPrincipal User user, @RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(UserSignUpResponse.from(userService.signUp(user.getId(), userCreateRequest)));
    }

    @Operation(summary = "닉네임 수정")
    @PutMapping
    public ResponseEntity<?> modify(@AuthenticationPrincipal User user, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.modifyNickname(user.getId(), userUpdateRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
}
