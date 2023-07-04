package com.ddungja.app.users.user.controller;

import com.ddungja.app.global.jwt.JwtProvider;
import com.ddungja.app.users.user.domain.KakaoProfile;
import com.ddungja.app.users.user.domain.User;
import com.ddungja.app.users.user.domain.request.KaKaoLoginRequest;
import com.ddungja.app.users.user.service.KakaoService;
import com.ddungja.app.users.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final KakaoService kakaoService;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KaKaoLoginRequest kaKaoLoginRequest) throws URISyntaxException {
        KakaoProfile kakaoProfile = kakaoService.getInfo(kaKaoLoginRequest);
        User user = userService.login(kakaoProfile);
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
    }

    private static ResponseCookie getRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofDays(14)).path("/")
//                .secure(true) //https를 쓸때 사용
//                .sameSite("None") //csrf 공격을 방지하기 위해 설정
//                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
                .httpOnly(true).build();
    }

    @Operation(summary = "리프레쉬 토큰 검증하고 엑세스토큰 반환")
    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(@CookieValue(value = "refreshToken") Cookie cookie) {
        String refreshToken = cookie.getValue();
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(user);
            refreshToken = jwtProvider.createRefreshToken(user);
            ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
        }
        return ResponseEntity.badRequest().body("refreshtoken 없음");
    }

}
