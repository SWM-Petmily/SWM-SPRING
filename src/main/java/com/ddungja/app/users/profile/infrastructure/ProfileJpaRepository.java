package com.ddungja.app.users.profile.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
}
