package com.ddungja.app.apply.repository;

import com.ddungja.app.apply.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    List<Apply> findBySellerId(Long id);
}
