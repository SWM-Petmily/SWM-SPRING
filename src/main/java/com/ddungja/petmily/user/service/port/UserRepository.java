package com.ddungja.petmily.user.service.port;

import com.ddungja.petmily.user.domain.user.User;

import java.util.Optional;

public interface UserRepository {
     Optional<User> findByEmail(String email);

    User save(User user);

    Optional<User> findUserById(Long userId);

    Optional<User> findById(Long userId);

    void certificationUpdateFalse();

    Optional<User> findByPhone(String phoneNumber);

    boolean existsById(Long userId);
}
