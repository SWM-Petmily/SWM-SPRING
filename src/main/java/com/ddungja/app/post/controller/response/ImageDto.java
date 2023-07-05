package com.ddungja.app.post.controller.response;

import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import com.ddungja.app.post.domain.post.Gender;
import com.ddungja.app.post.domain.post.Neutered;
import com.ddungja.app.post.domain.post.Post;
import com.ddungja.app.users.user.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class ImageDto {
    @Data
    public static class ImageRequest {
        private String url;
    }
}
