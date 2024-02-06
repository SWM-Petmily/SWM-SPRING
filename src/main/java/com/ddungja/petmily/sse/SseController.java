package com.ddungja.petmily.sse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class SseController {
    private final static Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect() {
        log.info("연결");
        SseEmitter sseEmitter = new SseEmitter(Duration.ofMinutes(1).toMillis());
        sseEmitter.onTimeout(() -> {
            //클라이언트 타임아웃
            log.info("time out");
            sseEmitter.complete();
        });
        sseEmitter.onCompletion(() -> {
            //클라이언트 연결 종료
            log.info("완료");
            userConnection.remove("user");

        });
        userConnection.put("user", sseEmitter);

        var event = SseEmitter.event().name("onopen");

        try {
            sseEmitter.send(event);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
        return sseEmitter;
    }

    @GetMapping("/push-event")
    public void pushEvent() {
        SseEmitter sseEmitter = userConnection.get("user");
        var event = SseEmitter.event().data("data");

        try {
            sseEmitter.send(event);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }
}
