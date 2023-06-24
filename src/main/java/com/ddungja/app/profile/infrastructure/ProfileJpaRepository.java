package com.ddungja.app.profile.infrastructure;

import com.ddungja.app.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
}
