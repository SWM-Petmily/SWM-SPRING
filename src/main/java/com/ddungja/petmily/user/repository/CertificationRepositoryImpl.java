package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.service.port.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CertificationRepositoryImpl implements CertificationRepository {

    private final CertificationJpaRepository certificationJpaRepository;
    @Override
    public Certification save(Certification certification) {
        return certificationJpaRepository.save(certification);
    }

    @Override
    public Optional<Certification> findFirstByUserIdOrderByIdDesc(Long userId) {
        return certificationJpaRepository.findFirstByUserIdOrderByIdDesc(userId);
    }

    @Override
    public void deleteAll() {
        certificationJpaRepository.deleteAll();
    }
}
