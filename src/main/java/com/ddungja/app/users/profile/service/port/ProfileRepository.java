package com.ddungja.app.users.profile.service.port;

import com.ddungja.app.users.profile.domain.Profile;

public interface ProfileRepository {
    Profile save(Profile profile);
}
