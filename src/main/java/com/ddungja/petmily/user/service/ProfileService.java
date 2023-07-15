package com.ddungja.petmily.user.service;


import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.user.domain.Experience;
import com.ddungja.petmily.user.domain.Profile;
import com.ddungja.petmily.user.domain.ProfileImage;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import com.ddungja.petmily.user.repository.ExperienceRepository;
import com.ddungja.petmily.user.repository.ProfileImageRepository;
import com.ddungja.petmily.user.repository.ProfileRepository;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final ProfileImageRepository profileImageRepository;

    @Transactional(readOnly = true)
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
        if (profileCreateRequest.getIsExperience()) {
            profileCreateRequest.getExperiences().forEach(experience -> experienceRepository.save(Experience.from(experience, profile)));
        }
        user.createProfile();
        return profile;
    }

    @Transactional
    public Profile modify(ProfileUpdateRequest profileUpdateRequest, Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(() -> new CustomException(PROFILE_NOT_FOUND));
        ProfileImage profileImage = profileImageRepository.findById(profileUpdateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        profile.update(profileUpdateRequest, profileImage);
        profile.deleteExperiences();
        if (profileUpdateRequest.getIsExperience()) {
            profileUpdateRequest.getExperiences().forEach(experience -> experienceRepository.save(Experience.from(experience, profile)));
        }
        return profile;
    }
}
