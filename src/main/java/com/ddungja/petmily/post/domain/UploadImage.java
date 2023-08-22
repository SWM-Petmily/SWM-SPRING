package com.ddungja.petmily.post.domain;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Getter
public class UploadImage {

    private final String fileName;
    private final Long size;

    public UploadImage(MultipartFile image) {
        isImage(Objects.requireNonNull(image.getContentType()));
        this.fileName = UUID.randomUUID() + "-" + image.getOriginalFilename(); // 파일 이름
        this.size = image.getSize(); // 파일 크기
        log.info("fileName: {}, size: {}, contentType: {}", fileName, size, image.getContentType());
    }

    private void isImage(String contentType) {
        if(Objects.equals(contentType, "image/png") || Objects.equals(contentType, "image/jpeg") || Objects.equals(contentType, "image/gif") || Objects.equals(contentType, "image/webp")){
            return;
        }
        throw new CustomException(ExceptionCode.IMAGE_NOT_FOUND);
    }

}
