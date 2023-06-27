package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageJpaRepository extends JpaRepository<ProfileImage, Long> {
}
