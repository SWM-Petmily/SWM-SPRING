package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post,Long>, PostQueryRepository {
    @EntityGraph(attributePaths = {"subCategory" , "mainCategory", "user", "diseases"})
    Optional<Post> findPostById(Long postId);

}
