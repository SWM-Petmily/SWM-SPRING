package com.ddungja.petmily.users.user.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.users.user.domain.KakaoProfile;
import com.ddungja.petmily.users.user.domain.User;
import com.ddungja.petmily.users.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.USER_NOT_FOUND;
import static com.ddungja.petmily.users.user.domain.ProviderType.KAKAO;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User login(KakaoProfile kakaoProfile) {
        return userRepository.findByEmail(kakaoProfile.getEmail()).orElseGet(() -> userRepository.save(User.builder().email(kakaoProfile.getEmail()).provider(KAKAO.name()).build()));
    }

    @Transactional
    public User get(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new CustomException(USER_NOT_FOUND));
    }
}
