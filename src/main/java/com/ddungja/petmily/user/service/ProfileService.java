package com.ddungja.petmily.user.service;


import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.user.domain.profile.Profile;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;
import com.ddungja.petmily.user.service.port.ProfileRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Builder
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;

    public Profile get(Long userId) {
        return profileRepository.findByUserId(userId).orElseThrow(() -> new CustomException(PROFILE_NOT_FOUND));
    }

    @Transactional
    public Profile create(MyProfileCreateRequest profileCreateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (user.isProfile()) throw new CustomException(PROFILE_ALREADY_EXISTS);
        ProfileImage profileImage = profileImageRepository.findById(profileCreateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        Profile profile = Profile.from(profileCreateRequest, profileImage, user);
        profileRepository.save(profile);
        user.createProfile();
        return profile;
    }

    @Transactional
    public Profile modify(ProfileUpdateRequest profileUpdateRequest, Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(() -> new CustomException(PROFILE_NOT_FOUND));
        ProfileImage profileImage = profileImageRepository.findById(profileUpdateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        profile.update(profileUpdateRequest, profileImage);
        return profile;
    }
}
