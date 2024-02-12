package com.ddungja.petmily.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Cacheable(cacheNames = "notice", key = "#id")
    public NoticeDto getNotice(Long id) {
        log.info("get notice service method call: {}", id);
        return noticeRepository.getNotice(id);

    }

    @CachePut(cacheNames = "notice", key = "#notice.id")
    public NoticeDto addNotice(NoticeDto notice) {
        log.info("service add notice method call: {}", notice);
        return noticeRepository.addNotice(notice);
    }


}
