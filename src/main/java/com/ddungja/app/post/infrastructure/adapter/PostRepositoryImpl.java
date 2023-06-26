package com.ddungja.app.post.infrastructure.adapter;

import com.ddungja.app.post.infrastructure.jpa.PostJpaRepository;
import com.ddungja.app.post.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;
}
