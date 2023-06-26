package com.ddungja.app.post.infrastructure.adapter;

import com.ddungja.app.post.infrastructure.jpa.ImageJpaRepository;
import com.ddungja.app.post.service.port.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {
    private final ImageJpaRepository imageJpaRepository;
}
