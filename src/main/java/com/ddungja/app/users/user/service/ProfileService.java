package com.ddungja.app.users.user.service;


import com.ddungja.app.common.domain.exception.CustomException;
import com.ddungja.app.common.domain.exception.ExceptionCode;
import com.ddungja.app.users.user.domain.*;
import com.ddungja.app.users.user.domain.request.ExperienceCreateRequest;
import com.ddungja.app.users.user.domain.request.ExperienceUpdateRequest;
import com.ddungja.app.users.user.domain.request.ProfileCreateRequest;
import com.ddungja.app.users.user.domain.request.ProfileUpdateRequest;
import com.ddungja.app.users.user.repository.ExperienceRepository;
import com.ddungja.app.users.user.repository.ProfileImageRepository;
import com.ddungja.app.users.user.repository.ProfileRepository;
import com.ddungja.app.users.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ddungja.app.common.domain.exception.ExceptionCode.PROFILE_IMAGE_NOT_FOUND;
import static com.ddungja.app.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final ProfileImageRepository profileImageRepository;

    @Transactional(readOnly = true)
    public Profile get(Long userId) {
        return profileRepository.findByUserId(userId).orElseThrow(() -> new CustomException(ExceptionCode.PROFILE_NOT_FOUND));
    }
    @Transactional
    public Profile create(ProfileCreateRequest profileCreateRequest, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (user.isProfile()) throw new CustomException(ExceptionCode.PROFILE_ALREADY_EXISTS);
        ProfileImage profileImage = profileImageRepository.findById(profileCreateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));
        Profile profile = Profile.from(profileCreateRequest, profileImage, user);
        profileRepository.save(profile);
        if (profileCreateRequest.isExperience()) {
            List<Experience> experiences = new ArrayList<>();
            for (ExperienceCreateRequest experience : profileCreateRequest.getExperiences()) {
                experiences.add(Experience.from(experience, profile));
            }
            experienceRepository.saveAll(experiences);
        }
        user.createProfile();
        return profile;
    }
    @Transactional
    public Profile update(ProfileUpdateRequest profileUpdateRequest, Long id) {
        Profile profile = profileRepository.findByUserId(id).orElseThrow(() -> new CustomException(ExceptionCode.PROFILE_NOT_FOUND));
        ProfileImage profileImage = profileImageRepository.findById(profileUpdateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));

        profile.update(profileUpdateRequest, profileImage);
        if (!profileUpdateRequest.isExperience()) {
            experienceRepository.deleteAll(profile.getExperiences());
            return profile;
        }
        for (ExperienceUpdateRequest experience : profileUpdateRequest.getExperiences()) {
            Experience updateExperience = experienceRepository.findById(experience.getId()).orElseThrow(() -> new CustomException(ExceptionCode.EXPERIENCE_NOT_FOUND));
            updateExperience.update(experience);
        }
        return profile;
    }
}
