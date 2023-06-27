package com.ddungja.app.users.user.repository;

import com.ddungja.app.users.user.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
