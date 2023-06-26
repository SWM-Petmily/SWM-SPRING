package com.ddungja.app.users.user.controller;

import com.ddungja.app.global.jwt.JwtProvider;
import com.ddungja.app.users.user.controller.response.ProfileCreateResponse;
import com.ddungja.app.users.user.domain.Profile;
import com.ddungja.app.users.user.domain.User;
import com.ddungja.app.users.user.domain.KakaoProfile;
import com.ddungja.app.users.user.service.KakaoService;
import com.ddungja.app.users.user.domain.ProfileCreateRequest;
import com.ddungja.app.users.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


    //카카오 로그인 되는지 확인용 코드
    @GetMapping("/kakao")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://kauth.kakao.com/oauth/authorize?client_id=c78d60a3b466b1b1c743a2bb745a1731&redirect_uri=http://localhost:8080/users/kakao/callback&response_type=code");
    }

    //카카오 로그인 되는지 확인용 코드
    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code") String code) throws URISyntaxException {
        KakaoProfile kakaoProfile = kakaoService.getInfo(code);
        User user = userService.login(kakaoProfile);
        String accessToken = jwtProvider.createAccessToken(user);
        log.info("accessToken = {}", accessToken);
        String refreshToken = jwtProvider.createRefreshToken(user);
        ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
    }


    //백엔드에서 카카오 로그인 받기
    @GetMapping("/kakao/login")
    public ResponseEntity<?> kakaoLogin(@RequestParam String tokenType, @RequestParam String kakaoAccessToken) throws URISyntaxException {
        KakaoProfile kakaoProfile = kakaoService.getInfo(tokenType, kakaoAccessToken);
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

    @PostMapping
    public ResponseEntity<?> createProfile(@AuthenticationPrincipal User user, @Valid @RequestBody ProfileCreateRequest profileCreateRequest) {
        Profile profile = userService.createProfile(profileCreateRequest, 1L);
        return ResponseEntity.ok(ProfileCreateResponse.from(profile));
    }



}
