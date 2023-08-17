package com.ddungja.petmily.report.repository;

import com.ddungja.petmily.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Boolean existsByUserIdAndPostId(Long userId, Long postId);
}
