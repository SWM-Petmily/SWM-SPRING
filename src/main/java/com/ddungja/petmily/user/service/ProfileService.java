package com.ddungja.petmily.user.service;


import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.user.domain.Experience;
import com.ddungja.petmily.user.domain.Profile;
import com.ddungja.petmily.user.domain.ProfileImage;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.ExperienceCreateRequest;
import com.ddungja.petmily.user.domain.request.ExperienceUpdateRequest;
import com.ddungja.petmily.user.domain.request.ProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import com.ddungja.petmily.user.repository.ExperienceRepository;
import com.ddungja.petmily.user.repository.ProfileImageRepository;
import com.ddungja.petmily.user.repository.ProfileRepository;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public Profile create(ProfileCreateRequest profileCreateRequest, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (user.isProfile()) throw new CustomException(PROFILE_ALREADY_EXISTS);
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
        Profile profile = profileRepository.findByUserId(id).orElseThrow(() -> new CustomException(PROFILE_NOT_FOUND));
        ProfileImage profileImage = profileImageRepository.findById(profileUpdateRequest.getProfileImageId()).orElseThrow(() -> new CustomException(PROFILE_IMAGE_NOT_FOUND));

        profile.update(profileUpdateRequest, profileImage);
        if (!profileUpdateRequest.isExperience()) {
            experienceRepository.deleteAll(profile.getExperiences());
            return profile;
        }
        for (ExperienceUpdateRequest experience : profileUpdateRequest.getExperiences()) {
            Experience updateExperience = experienceRepository.findById(experience.getId()).orElseThrow(() -> new CustomException(EXPERIENCE_NOT_FOUND));
            updateExperience.update(experience);
        }
        return profile;
    }
}
