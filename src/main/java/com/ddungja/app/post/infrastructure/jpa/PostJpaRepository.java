package com.ddungja.app.post.infrastructure.jpa;

import com.ddungja.app.post.infrastructure.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity,Long> {
}
