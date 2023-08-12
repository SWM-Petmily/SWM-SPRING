package com.ddungja.petmily.user.service.port;

import com.ddungja.petmily.user.domain.certification.Certification;

import java.util.Optional;

public interface CertificationRepository {
    Certification save(Certification certification);

    Optional<Certification> findFirstByUserIdOrderByIdDesc(long userId);

    void deleteAll();
}
