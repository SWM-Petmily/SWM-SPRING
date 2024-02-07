package com.ddungja.petmily.sse;

import com.ddungja.petmily.user.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class SseController {
    private final SseConnectionPool sseConnectionPool;

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect() {
        log.info("connect");
        UserSseConnection userSseConnection = UserSseConnection.connect("user", sseConnectionPool);
        sseConnectionPool.addSession("user", userSseConnection);
        return userSseConnection.getSseEmitter();
    }

    @GetMapping("/push-event")
    public void pushEvent() {
        log.info("push-event");
        UserSseConnection userSseConnection = sseConnectionPool.getSession("user");
        User user = User.builder().id(1L).nickname("nickname").build();
        userSseConnection.sendMessage(user);
    }
}
