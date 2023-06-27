package com.ddungja.app.users.apply.repository;

import com.ddungja.app.users.apply.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
