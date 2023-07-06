package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.global.jwt.JwtProvider;
import com.ddungja.petmily.user.domain.KakaoProfile;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.KakaoService;
import com.ddungja.petmily.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "카카오 로그인하기")
    @GetMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestParam String tokenType, @RequestParam String kakaoAccessToken) throws URISyntaxException {
        log.info("카카오 로그인 요청 tokenType = {}, kakaoAccessToken = {}", tokenType, kakaoAccessToken);
        KakaoProfile kakaoProfile = kakaoService.getInfo(tokenType, kakaoAccessToken);
        User user = userService.login(kakaoProfile);
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
        return ResponseEntity.ok().header(SET_COOKIE, refreshTokenCookie.toString()).header(AUTHORIZATION, accessToken).build();
    }

    private static ResponseCookie getRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofDays(14)).path("/")
//                .secure(true) //https를 쓸때 사용
//                .sameSite("None") //csrf 공격을 방지하기 위해 설정
//                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
                .httpOnly(true)
                .build();
    }

    @Operation(summary = "리프레쉬 토큰 검증하고 엑세스토큰 반환")
    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(@CookieValue(value = "refreshToken") Cookie cookie) {
        log.info("refresh 요청 = {}", cookie.getValue());
        String refreshToken = cookie.getValue();
        log.debug("refreshToken = {}", refreshToken);
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(user);
            refreshToken = jwtProvider.createRefreshToken(user);
            ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
            return ResponseEntity.ok().header(SET_COOKIE, refreshTokenCookie.toString()).header(AUTHORIZATION, accessToken).build();
        }
        throw new CustomException(REFRESH_TOKEN_VALIDATION_FAILED);
    }

    @Operation(summary = "권한테스트")
    @GetMapping("/authorization")
    public ResponseEntity<?> authroizationtest(){
        log.debug("권한 테스트");
        return ResponseEntity.ok("토큰이 존재합니다");
    }

    @Operation(summary = "테스트용 엑세스토큰 반환")
    @GetMapping("/test/token")
    public ResponseEntity<?> testAccessToken() {
        String testAccessToken = jwtProvider.createTestAccessToken(1L);
        return ResponseEntity.ok(testAccessToken);
    }
}
