package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long postId);

    @EntityGraph(attributePaths = {"like", "subCategory"})
    Page<Post> findByUserId(Long userId, Pageable pageable);
}
