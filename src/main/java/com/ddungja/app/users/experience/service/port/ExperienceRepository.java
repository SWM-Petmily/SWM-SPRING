package com.ddungja.app.users.experience.service.port;

import com.ddungja.app.users.experience.domain.Experience;

import java.util.List;

public interface ExperienceRepository {
    List<Experience> saveAll(List<Experience> experiences);
}
