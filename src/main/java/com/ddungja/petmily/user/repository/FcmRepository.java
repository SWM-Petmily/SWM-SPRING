package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.user.Fcm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmRepository extends JpaRepository<Fcm, String> {
}
