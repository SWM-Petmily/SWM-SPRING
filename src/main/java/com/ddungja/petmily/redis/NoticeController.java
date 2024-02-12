package com.ddungja.petmily.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/get")
    public NoticeDto getNotice(@RequestParam Long id) {
        log.info("get notice controller method call: {}", id);
        return noticeService.getNotice(id);

    }

    @GetMapping("/add")
    public NoticeDto addNotice(@RequestParam String notice) {
        log.info("add notice controller method call: {}", notice);
        return noticeService.addNotice(new NoticeDto(null, notice));
    }
}
