package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.user.controller.port.KakaoService;
import com.ddungja.petmily.user.controller.response.UserLoginResponse;
import com.ddungja.petmily.user.controller.response.UserMyPageResponse;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.request.*;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.CertificationService;
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

import static com.ddungja.petmily.common.exception.ExceptionCode.REFRESH_TOKEN_VALIDATION_FAILED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final KakaoService kakaoService;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final CertificationService certificationService;
    private final ApplyService applyService;
    private final LikeService likeService;

    @Operation(summary = "카카오 로그인")
    @ApiResponse(responseCode = "200", description = "카카오 로그인 성공", content = @Content(schema = @Schema(implementation = UserLoginResponse.class)))
    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody KaKaoLoginRequest kaKaoLoginRequest) throws URISyntaxException {
        log.info("카카오 로그인 kaKaoLoginRequest = {}", kaKaoLoginRequest);
        KakaoProfile kakaoProfile = kakaoService.getInfo(kaKaoLoginRequest);
        User user = userService.kakagoLogin(kakaoProfile);
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        return ResponseEntity.ok().body(UserLoginResponse.from(user, accessToken, refreshToken));
    }

    @Operation(summary = "애플 로그인")
    @ApiResponse(responseCode = "200", description = "애플 로그인 성공", content = @Content(schema = @Schema(implementation = UserLoginResponse.class)))
    @PostMapping("/apple")
    public ResponseEntity<?> applyLogin(@RequestBody AppleLoginRequest appleLoginRequest) {
        log.debug("애플 로그인");
        User user = userService.appleLogin(appleLoginRequest);
        userService.appleLogin(appleLoginRequest);
        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user);
        return ResponseEntity.ok().body(UserLoginResponse.from(user, accessToken, refreshToken));
    }

    @Operation(summary = "휴대전화 인증번호 발송 20원까임")
    @ApiResponse(responseCode = "204", description = "휴대전화 인증번호 발송 성공")
    @PostMapping("/certification/send")
    public ResponseEntity<?> sendCertificationNumber(@AuthenticationPrincipal User user, @RequestBody CertificationPhoneNumberRequest certificationPhoneNumberRequest) {
        log.info("휴대전화 인증번호 발송 user = {} phoneNumber = {}", user.getId(), certificationPhoneNumberRequest.getPhoneNumber());
        certificationService.sendCertificationNumber(user.getId(), certificationPhoneNumberRequest.getPhoneNumber());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "휴대전화 인증번호 발송 테스트용 인증번호[123456] 고정")
    @ApiResponse(responseCode = "204", description = "휴대전화 인증번호 발송 성공")
    @PostMapping("/certification/send/test")
    public ResponseEntity<?> sendCertificationNumberTest(@AuthenticationPrincipal User user, @RequestBody CertificationPhoneNumberRequest certificationPhoneNumberRequest) {
        log.info("휴대전화 인증번호 발송 테스트용 인증번호[123456] 고정 user = {} phoneNumber = {}", user.getId(), certificationPhoneNumberRequest.getPhoneNumber());
        certificationService.sendCertificationNumberTest(user.getId(), certificationPhoneNumberRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "휴대전화 인증번호 확인")
    @ApiResponse(responseCode = "204", description = "휴대전화 인증번호 확인 성공")
    @PostMapping("/certification/verify")
    public ResponseEntity<?> certificationVerify(@AuthenticationPrincipal User user, @RequestBody CertificationVerifyRequest certificationPhoneVerifyRequest) {
        log.info("휴대전화 인증번호 확인 user = {}, certificationPhoneVerifyRequest = {}", user.getId(), certificationPhoneVerifyRequest);
        certificationService.certificationVerify(user.getId(), certificationPhoneVerifyRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원가입")
    @ApiResponse(responseCode = "204", description = "회원가입 성공")
    @PostMapping("/sign")
    public ResponseEntity<?> certificationVerify(@AuthenticationPrincipal User user, @RequestBody UserCreateRequest userCreateRequest) {
        log.info("회원가입 user = {} userCreateRequest = {}, ", user.getId(), userCreateRequest);
        userService.signUp(user.getId(), userCreateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "프로필 사진, 닉네임 수정")
    @ApiResponse(responseCode = "204", description = "닉네임 수정 성공")
    @PutMapping
    public ResponseEntity<?> modify(@AuthenticationPrincipal User user, @RequestBody UserUpdateRequest userUpdateRequest) {
        log.info("프로필 사진, 닉네임 수정 user = {}", user.getId());
        userService.modifyNickname(user.getId(), userUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "마이페이지 조회")
    @ApiResponse(responseCode = "200", description = "마이페이지 조회 성공", content = @Content(schema =  @Schema(implementation = UserMyPageResponse.class)))
    @GetMapping("/myPage")
    public ResponseEntity<?> myPage(@AuthenticationPrincipal User user) {
        log.info("마이페이지 조회 user = {}", user.getId());
        int applyCount = applyService.getApplyCount(user.getId());
        int likeCount = likeService.getLikeCountByUser(user);
        return ResponseEntity.ok().body(UserMyPageResponse.from(userService.getMyPage(user.getId()), applyCount, likeCount));
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
