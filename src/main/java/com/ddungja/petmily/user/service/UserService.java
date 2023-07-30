package com.ddungja.petmily.user.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.common.domain.exception.ExceptionCode;
import com.ddungja.petmily.user.domain.Certification;
import com.ddungja.petmily.user.domain.KakaoProfile;
import com.ddungja.petmily.user.domain.ProviderType;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
import com.ddungja.petmily.user.repository.CertificationRepository;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

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

    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    @Transactional
    public void signUp(Long userId, UserCreateRequest userCreateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Certification certification = certificationRepository.findFirstByUserIdOrderByIdDesc(user.getId()).orElseThrow(() -> new CustomException(ExceptionCode.CERTIFICATION_NOT_FOUND));
        certification.signUpVerify();
        user.signUp(userCreateRequest, certification);
    }

    @Transactional
    public void modifyNickname(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        user.update(userUpdateRequest);
    }

    @Transactional
    public User appleLogin(String email) {
        log.info("애플로그인 = {} ", email);
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

