package com.ddungja.petmily.post.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;


    @Transactional
    public List<String> upload(List<MultipartFile> multipartFiles) throws IOException {
        List<String> images = new ArrayList<>();
        for (MultipartFile image : multipartFiles) {
            String fileName = UUID.randomUUID() + "-" + image.getOriginalFilename(); // 파일 이름
            long size = image.getSize(); // 파일 크기
            log.debug("fileName: {}, size: {}, contentType: {}", fileName, size, image.getContentType());
            if (isImage(Objects.requireNonNull(image.getContentType()))) {
                images.add(uploadImage(image, fileName, size, bucket));
            }
        }
        return images;
    }

    private String uploadImage(MultipartFile multipartFile, String fileName, long size, String bucket) throws IOException {
        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);
        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return amazonS3Client.getUrl(bucket, fileName).toString(); // 접근가능한 URL 가져오기
    }

    private boolean isImage(String contentType) {
        return contentType.equals("image/png") || contentType.equals("image/jpeg") || contentType.equals("image/gif") || contentType.equals("image/webp");
    }

}
