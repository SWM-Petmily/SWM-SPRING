package com.ddungja.app.users.user.service;

import com.ddungja.app.common.domain.exception.CustomException;
import com.ddungja.app.users.user.domain.*;
import com.ddungja.app.users.user.service.port.ExperienceRepository;
import com.ddungja.app.users.user.service.port.ProfileRepository;
import com.ddungja.app.users.user.service.port.ProfileImageRepository;
import com.ddungja.app.users.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ddungja.app.common.domain.exception.ExceptionCode.PROFILE_IMAGE_NOT_FOUND;
import static com.ddungja.app.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ExperienceRepository experienceRepository;
    private final ProfileImageRepository profileImageRepository;

    @Transactional
    public User login(KakaoProfile kakaoProfile) {
        return userRepository.findByEmail(kakaoProfile.getEmail())
                .orElseGet(() -> userRepository.save(User.builder()
                        .email(kakaoProfile.getEmail())
                        .provider("kakao")
                        .build()));
    }

    @Transactional
    public Profile createProfile(ProfileCreateRequest profileCreateRequest, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        ProfileImage profileImage = profileImageRepository.findById(profileCreateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        Profile profile = Profile.from(profileCreateRequest, profileImage, user);
        profile = profileRepository.save(profile);

        if (profileCreateRequest.isExperience()) {
            List<Experience> experiences = new ArrayList<>();
            for (Experience experience : profileCreateRequest.getExperiences()) {
                experiences.add(Experience.from(experience, profile));
            }
            experienceRepository.saveAll(experiences);
        }
        return profile;
    }
}
