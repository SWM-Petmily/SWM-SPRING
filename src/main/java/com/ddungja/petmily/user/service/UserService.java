package com.ddungja.petmily.user.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.exception.ExceptionCode;
import com.ddungja.petmily.user.domain.apple.AppleOAuthUserProvider;
import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.AppleLoginRequest;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
import com.ddungja.petmily.user.domain.user.ProviderType;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.CertificationRepository;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.exception.ExceptionCode.PROFILE_IMAGE_NOT_FOUND;
import static com.ddungja.petmily.common.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@Builder
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final AppleOAuthUserProvider appleOAuthUserProvider;
    private final ProfileImageRepository profileImageRepository;

    @Transactional
    public User kakagoLogin(KakaoProfile kakaoProfile) {
        log.info("카카오 로그인 kakaoProfile = {}", kakaoProfile);
        return userRepository.findByEmail(kakaoProfile.getEmail()).orElseGet(() -> userRepository.save(User.builder()
                .email(kakaoProfile.getEmail())
                .provider(ProviderType.KAKAO)
                .isProfile(false)
                .isCertification(false)
                .build()));
    }

    public User getMyPage(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    @Transactional
    public void signUp(Long userId, UserCreateRequest userCreateRequest) {
        ProfileImage profileImage = profileImageRepository.findById(1L).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Certification certification = certificationRepository.findFirstByUserIdOrderByIdDesc(user.getId()).orElseThrow(() -> new CustomException(ExceptionCode.CERTIFICATION_NOT_FOUND));
        certification.signUpVerify();
        user.signUp(userCreateRequest, certification, profileImage);
    }

    @Transactional
    public void modify(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        ProfileImage profileImage = profileImageRepository.findById(userUpdateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        user.update(userUpdateRequest.getNickname(), profileImage);
    }

    @Transactional
    public User appleLogin(AppleLoginRequest appleLoginRequest) {
        log.info("애플로그인 = {} ", appleLoginRequest.getIdToken());
        String email = appleOAuthUserProvider.getApplePlatformMember(appleLoginRequest.getIdToken());
        return userRepository.findByEmail(email).orElseGet(() -> userRepository.save(User.builder()
                .email(email)
                .provider(ProviderType.APPLE)
                .isProfile(false)
                .isCertification(false)
                .build()));
    }

    @Transactional
    public void reset() {
        User user = userRepository.findById(1L).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        userRepository.certificationUpdateFalse();
        certificationRepository.deleteAll();
        user.reset();
    }
}

