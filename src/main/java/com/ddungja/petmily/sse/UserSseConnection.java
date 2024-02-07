package com.ddungja.petmily.sse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;

@Getter
public class UserSseConnection {

    private final String uniqueKey;
    private final SseEmitter sseEmitter;
    private final ConnectionPool<String, UserSseConnection> connectionPool;
    private final ObjectMapper objectMapper = new ObjectMapper();


    protected UserSseConnection(String uniqueKey, ConnectionPool<String, UserSseConnection> connectionPool) {
        this.uniqueKey = uniqueKey;
        this.sseEmitter = new SseEmitter(Duration.ofMinutes(1).toMillis());
        this.connectionPool = connectionPool;

        //ontimeout
        //클라이언트 타임아웃
        this.sseEmitter.onTimeout(this.sseEmitter::complete);
        //oncompletion
        this.sseEmitter.onCompletion(() -> {
            this.connectionPool.onCompletionCallback(this);
            //클라이언트 연결 종료
        });
        //onopen
        connectionPool.addSession(uniqueKey, this);
        sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(String uniqueKey, ConnectionPool<String, UserSseConnection> connectionPool) {
        return new UserSseConnection(uniqueKey, connectionPool);
    }

    public void sendMessage(String eventName, Object data) {
        try {
            var json = objectMapper.writeValueAsString(data);
            var event = SseEmitter.event().name(eventName).data(json);
            sseEmitter.send(event);
        } catch (Exception e) {
            sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data) {
        try {
            var json = objectMapper.writeValueAsString(data);
            var event = SseEmitter.event().data(json);
            sseEmitter.send(event);
        } catch (Exception e) {
            sseEmitter.completeWithError(e);
        }
    }
}

