package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phoneNumber);

    @Modifying
    @Query("update User u set u.isCertification = false")
    void certificationUpdateFalse();

    @EntityGraph(attributePaths = {"profileImage"})
    Optional<User> findUserById(Long userId);
}
