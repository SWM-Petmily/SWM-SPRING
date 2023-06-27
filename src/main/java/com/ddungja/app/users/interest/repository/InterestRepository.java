package com.ddungja.app.users.interest.repository;

import com.ddungja.app.users.interest.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
