package com.ddungja.app.users.user.service;

import com.ddungja.app.users.user.domain.KakaoProfile;
import com.ddungja.app.users.user.domain.User;
import com.ddungja.app.users.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User login(KakaoProfile kakaoProfile) {
        return userRepository.findByEmail(kakaoProfile.getEmail()).orElseGet(() -> userRepository.save(User.builder().email(kakaoProfile.getEmail()).provider("kakao").build()));
    }

}
