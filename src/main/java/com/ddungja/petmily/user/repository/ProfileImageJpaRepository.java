package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.profile.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageJpaRepository extends JpaRepository<ProfileImage, Long> {
}
