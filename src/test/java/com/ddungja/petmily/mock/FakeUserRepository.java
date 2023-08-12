package com.ddungja.petmily.mock;

import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {

    private long autoGeneratedId = 1L;
    private final List<User> data = new ArrayList<>();

    @Override
    public Optional<User> findByEmail(String email) {
        return data.stream().filter(item -> item.getEmail().equals(email)).findAny();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            data.add(User.builder()
                    .id(autoGeneratedId++)
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .phone(user.getPhone())
                    .provider(user.getProvider())
                    .isProfile(user.isProfile())
                    .isCertification(user.isCertification())
                    .profileImage(user.getProfileImage())
                    .build());
            return user;
        }
        data.removeIf(item -> item.getId().equals(user.getId()));
        data.add(user);
        return user;
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return data.stream().filter(item -> item.getId().equals(userId)).findAny();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return data.stream().filter(item -> item.getId().equals(userId)).findAny();
    }

    @Override
    public void certificationUpdateFalse() {
        
    }

    @Override
    public Optional<User> findByPhone(String phoneNumber) {
        return data.stream().filter(item -> item.getPhone().equals(phoneNumber)).findAny();
    }
}
