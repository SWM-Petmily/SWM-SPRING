package com.ddungja.app.posts.post.infrastructure;

import com.ddungja.app.posts.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity,Long> {
}
