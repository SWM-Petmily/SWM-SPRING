package com.ddungja.petmily.report.repository;

import com.ddungja.petmily.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Boolean existsByUserIdAndPostId(Long userId, Long postId);

    List<Report> findByUserId(Long userId);
}
