package com.ddungja.app.interest.repository;

import com.ddungja.app.interest.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
