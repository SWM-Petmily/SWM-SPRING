package com.ddungja.petmily.user.service;

import com.ddungja.petmily.user.domain.KakaoProfile;
import com.ddungja.petmily.user.domain.ProviderType;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User login(KakaoProfile kakaoProfile) {
        return userRepository.findByEmail(kakaoProfile.getEmail()).orElseGet(() -> userRepository.save(User.builder().email(kakaoProfile.getEmail()).provider(ProviderType.KAKAO.name()).build()));
    }
}
