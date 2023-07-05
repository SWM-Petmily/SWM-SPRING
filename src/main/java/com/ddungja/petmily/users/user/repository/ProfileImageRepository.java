package com.ddungja.petmily.users.user.repository;

import com.ddungja.petmily.users.user.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
