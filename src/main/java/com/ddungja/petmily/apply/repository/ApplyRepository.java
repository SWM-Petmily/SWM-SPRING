package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    List<Apply> findBySellerId(Long id);
}
