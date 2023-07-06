package com.ddungja.petmily.apply.service;


import com.ddungja.petmily.apply.domain.ApplyExperience;
import com.ddungja.petmily.apply.repository.ApplyExperienceRepository;
import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.common.domain.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyExperienceService {
    private final ApplyExperienceRepository applyExperienceRepository;


    public List<ApplyExperience> getApplyId(Long applyId) {
        return applyExperienceRepository.findByApplyId(applyId);
    }
}
