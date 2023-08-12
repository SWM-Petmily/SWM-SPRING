package com.ddungja.petmily.mock;

import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeProfileImageRepository implements ProfileImageRepository {

    private long autoGeneratedId = 1L;
    private final List<ProfileImage> data = new ArrayList<>();

    @Override
    public Optional<ProfileImage> findById(Long profileImageId) {
        return data.stream().filter(item -> item.getId().equals(profileImageId)).findAny();
    }

    @Override
    public ProfileImage save(ProfileImage profileImage) {
        if (profileImage.getId() == null || profileImage.getId() == 0) {
            ProfileImage newProfileImage = ProfileImage.builder()
                    .id(autoGeneratedId++)
                    .url("https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk")
                    .build();
            data.add(newProfileImage);
            return newProfileImage;
        }
        data.removeIf(item -> item.getId().equals(profileImage.getId()));
        return profileImage;
    }
}