package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplyCustomRepository {
    Page<Apply> getApplyList(Long userId, ApprovalType approval, Pageable pageable);

}
