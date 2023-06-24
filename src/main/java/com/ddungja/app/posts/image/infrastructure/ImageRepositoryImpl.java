package com.ddungja.app.posts.image.infrastructure;

import com.ddungja.app.posts.image.service.port.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {
    private final ImageJpaRepository imageJpaRepository;
}
