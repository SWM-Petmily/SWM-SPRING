package com.ddungja.app.users.user.service.port;

import com.ddungja.app.users.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    User save(User user);
}
