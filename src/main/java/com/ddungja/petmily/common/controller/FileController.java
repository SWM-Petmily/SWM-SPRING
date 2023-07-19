package com.ddungja.petmily.common.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private static final String BUCKET = "swm-petmily"; // Bucket 이름

    private final AmazonS3Client amazonS3Client;

    @GetMapping("/upload")
    public ResponseEntity<Object> upload(@RequestPart MultipartFile[] images) throws Exception {
        List<String> imageList = new ArrayList<>();
        for (MultipartFile multipartFile : images) {
            String originalName = multipartFile.getOriginalFilename(); // 파일 이름
            long size = multipartFile.getSize(); // 파일 크기
            String contentType = multipartFile.getContentType();
            log.debug("originalName: {}, size: {}, contentType: {}", originalName, size, contentType);
            if (contentType.equals("image/png") || contentType.equals("image/jpeg") || contentType.equals("image/gif") || contentType.equals("image/webp")) {
                ObjectMetadata objectMetaData = new ObjectMetadata();
                objectMetaData.setContentType(multipartFile.getContentType());
                objectMetaData.setContentLength(size);

                // S3에 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(BUCKET, originalName, multipartFile.getInputStream(), objectMetaData)
                                .withCannedAcl(CannedAccessControlList.PublicRead)
                );
                String s3ImageLink = amazonS3Client.getUrl(BUCKET, originalName).toString(); // 접근가능한 URL 가져오기
                imageList.add(s3ImageLink);
            }
        }
        return ResponseEntity.ok(imageList);
    }

}