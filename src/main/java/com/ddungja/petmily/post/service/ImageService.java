package com.ddungja.petmily.post.service;


import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ddungja.petmily.post.domain.type.ImageType.POST;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    @Transactional
    public List<Image> getImages(Long postId) {
        return imageRepository.findByPostIdAndImageType(postId, POST);
    }

}
