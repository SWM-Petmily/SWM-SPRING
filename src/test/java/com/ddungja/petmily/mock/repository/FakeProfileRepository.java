package com.ddungja.petmily.mock.repository;

import com.ddungja.petmily.user.domain.profile.Profile;
import com.ddungja.petmily.user.service.port.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeProfileRepository implements ProfileRepository {

    private long autoGeneratedId = 1L;
    private final List<Profile> data = new ArrayList<>();

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return data.stream()
                .filter(item -> item.getUser().getId().equals(userId))
                .findAny();
    }

    @Override
    public Profile save(Profile profile) {
        if (profile.getId() == null || profile.getId() == 0) {
            Profile newProfile = Profile.builder()
                    .id(autoGeneratedId++)
                    .job(profile.getJob())
                    .isExperience(profile.isExperience())
                    .openTalk(profile.getOpenTalk())
                    .profileImage(profile.getProfileImage())
                    .user(profile.getUser())
                    .people(profile.getPeople())
                    .region(profile.getRegion())
                    .environment(profile.getEnvironment())
                    .experiences(profile.getExperiences())
                    .build();
            data.add(newProfile);
            return newProfile;
        }
        data.removeIf(item -> item.getId().equals(profile.getId()));
        data.add(profile);
        return profile;
    }

    @Override
    public void deleteByUserId(Long userId) {
        data.removeIf(item -> item.getUser().getId().equals(userId));
    }
}
