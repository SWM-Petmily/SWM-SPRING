package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.service.PostService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostRepositoryTest {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;
    
    @Test
    @Transactional
    public void setPostService(){
        //given
        List<Post> all = postRepository.findAll();
        for (Post post : all) {
            System.out.println(post.getImages());
        }
        //when
        //then
    }

}