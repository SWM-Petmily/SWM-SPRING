package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
