package com.ddungja.petmily.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class NoticeRepository {

    private final List<NoticeDto> noticeList = new ArrayList<>();


    public NoticeDto getNotice(Long id) {
        log.info("get notice method call: {}", id);
        return noticeList.stream()
                .filter(noticeDto -> noticeDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 공지사항이 없습니다."));
    }


    public NoticeDto addNotice(NoticeDto notice) {
        log.info("repository add notice method call: {}", notice);
        notice.setId(noticeList.size() + 1L);
        return notice;

    }


}
