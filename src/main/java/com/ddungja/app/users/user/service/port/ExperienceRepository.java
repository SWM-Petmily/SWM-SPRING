package com.ddungja.app.users.user.service.port;

import com.ddungja.app.users.user.domain.Experience;

import java.util.List;

public interface ExperienceRepository {
    List<Experience> saveAll(List<Experience> experiences);
}
