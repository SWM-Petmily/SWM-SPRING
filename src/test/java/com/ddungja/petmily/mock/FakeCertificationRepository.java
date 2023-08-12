package com.ddungja.petmily.mock;

import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.service.port.CertificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeCertificationRepository implements CertificationRepository {

    private long autoGeneratedId = 1L;
    private final List<Certification> data = new ArrayList<>();

    @Override
    public Certification save(Certification certification) {
        if (certification.getId() == null || certification.getId() == 0) {
            data.add(Certification.builder()
                    .id(autoGeneratedId++)
                    .phoneNumber(certification.getPhoneNumber())
                    .user(certification.getUser())
                    .certificationNumber(certification.getCertificationNumber())
                    .isCertification(certification.isCertification())
                    .expiredAt(certification.getExpiredAt())
                    .build());
            return certification;
        }
        data.removeIf(item -> item.getId().equals(certification.getId()));
        data.add(certification);
        return certification;
    }

    @Override
    public Optional<Certification> findFirstByUserIdOrderByIdDesc(Long userId) {
        return data.stream().filter(item -> item.getUser().getId().equals(userId)).findAny();
    }

    @Override
    public void deleteAll() {

    }
}
