package com.ddungja.app.users.user.service.port;

import com.ddungja.app.users.user.domain.Profile;

public interface ProfileRepository {
    Profile save(Profile profile);
}
