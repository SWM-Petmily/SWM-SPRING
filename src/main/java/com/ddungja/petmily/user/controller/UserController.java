package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.user.controller.response.CertificationPhoneNumberResponse;
import com.ddungja.petmily.user.controller.response.CertificationResponse;
import com.ddungja.petmily.user.controller.response.TokenRefreshResponse;
import com.ddungja.petmily.user.controller.response.UserLoginResponse;
import com.ddungja.petmily.user.domain.Certification;
import com.ddungja.petmily.user.domain.KakaoProfile;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.CertificationPhoneVerifyRequest;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
import com.ddungja.petmily.user.service.CoolSmsService;
import com.ddungja.petmily.user.service.KakaoService;
import com.ddungja.petmily.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/certification/send")
    public ResponseEntity<?> sendCertificationNumber(@AuthenticationPrincipal User user, String phoneNumber) {
        log.info("휴대전화 인증번호 발송 user = {} phoneNumber = {}", user.getId(), phoneNumber);
        Certification certification = coolSmsService.sendCertificationNumber(user.getId(), phoneNumber);
        return ResponseEntity.ok().body(CertificationPhoneNumberResponse.from(certification));
    }

    @Operation(summary = "휴대전화 인증번호 확인")
    @ApiResponse(responseCode = "200", description = "휴대전화 인증번호 확인 성공")
    @PostMapping("/certification/verify")
    public ResponseEntity<?> certificationVerify(@AuthenticationPrincipal User user, @RequestBody CertificationPhoneVerifyRequest certificationPhoneVerifyRequest) {
        log.info("휴대전화 인증번호 확인 user = {}, certificationPhoneVerifyRequest = {}", user.getId(), certificationPhoneVerifyRequest);
        coolSmsService.certificationVerify(user.getId(), certificationPhoneVerifyRequest);
        return ResponseEntity.ok().body("휴대전화 인증 성공");
    }

    @Operation(summary = "회원가입")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("/sign")
    public ResponseEntity<?> certificationVerify(@AuthenticationPrincipal User user, @RequestBody UserCreateRequest userCreateRequest) {
        log.info("회원가입 user = {} userCreateRequest = {}, ", user.getId(), userCreateRequest);
        userService.signUp(user.getId(), userCreateRequest);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @Operation(summary = "닉네임 수정")
    @ApiResponse(responseCode = "200", description = "닉네임 수정 성공")
    @PutMapping
    public ResponseEntity<?> modify(@AuthenticationPrincipal User user, @RequestBody UserUpdateRequest userUpdateRequest) {
        log.info("닉네임 수정 user = {}", user.getId());
        userService.modifyNickname(user.getId(), userUpdateRequest);
        return ResponseEntity.ok().body("닉네임 수정 성공");
    }

    @Operation(summary = "엑세스 토큰/리프레시 토큰 재발급")
    @ApiResponse(responseCode = "200", description = "엑세스 토큰/리프레시 토큰 재발급 성공", content = @Content(schema = @Schema(implementation = TokenRefreshResponse.class)))
    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(String refreshToken) {
        log.info("토큰 refresh 요청 = {}", refreshToken);
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            User user = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(user);
            refreshToken = jwtProvider.createRefreshToken(user);
            return ResponseEntity.ok().body(TokenRefreshResponse.from(accessToken, refreshToken));
        }
        throw new CustomException(REFRESH_TOKEN_VALIDATION_FAILED);
    }
}
