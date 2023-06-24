package com.ddungja.app.users.apply.infrastructure;

import com.ddungja.app.users.apply.service.port.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepository {
    private final ApplyJpaRepository applyJpaRepository;
}
