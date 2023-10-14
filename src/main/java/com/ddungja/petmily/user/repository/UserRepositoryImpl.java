package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
    @Override
    public Optional<User> findUserById(Long userId) {
        return userJpaRepository.findUserById(userId);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public void certificationUpdateFalse() {
        userJpaRepository.certificationUpdateFalse();
    }

    @Override
    public Optional<User> findByPhone(String phoneNumber) {
        return userJpaRepository.findByPhone(phoneNumber);
    }

    @Override
    public boolean existsById(Long userId) {
        return userJpaRepository.existsById(userId);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }
}
