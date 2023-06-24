package com.ddungja.app.users.interest.infrastructure;

import com.ddungja.app.users.interest.service.port.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepository {

    private final InterestJpaRepository interestJpaRepository;
}
