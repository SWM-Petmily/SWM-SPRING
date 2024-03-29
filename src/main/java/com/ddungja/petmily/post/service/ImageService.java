package com.ddungja.petmily.post.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.exception.ExceptionCode;
import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.UploadImage;
import com.ddungja.petmily.post.domain.type.ImageType;
import com.ddungja.petmily.post.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageService {

    @Value("${s3.bucket}")
    private String bucket;
    private final ImageRepository imageRepository;
    private final AmazonS3Client amazonS3Client;

    @Transactional
    public List<Image> upload(Post post, List<MultipartFile> multipartFiles, ImageType imageType) throws IOException {
        List<Image> saveImageList = new ArrayList<>();
        for (MultipartFile image : multipartFiles) {
                UploadImage uploadImage = new UploadImage(image);
                String url = uploadImage(image, uploadImage, bucket);
                saveImageList.add(Image.builder()
                        .imageType(imageType)
                        .post(post)
                        .url(url)
                        .build());
        }
        return imageRepository.saveAll(saveImageList);
    }


    @Transactional
    public void delete(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new CustomException(ExceptionCode.IMAGE_NOT_FOUND));
        if(!amazonS3Client.doesObjectExist(bucket, image.getUrl())){
            throw new CustomException(ExceptionCode.S3_IMAGE_NOT_FOUND);
        }
        amazonS3Client.deleteObject(bucket, image.getUrl());
        imageRepository.delete(image);
    }

    private String uploadImage(MultipartFile multipartFile,UploadImage uploadImage, String bucket) throws IOException {
        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(uploadImage.getSize());
        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, uploadImage.getFileName(), multipartFile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return amazonS3Client.getUrl(bucket, uploadImage.getFileName()).toString(); // 접근가능한 URL 가져오기
    }



    public List<Image> getImages(Long postId, ImageType imageType) {
        return imageRepository.findByPostIdAndImageType(postId, imageType);
    }

}
