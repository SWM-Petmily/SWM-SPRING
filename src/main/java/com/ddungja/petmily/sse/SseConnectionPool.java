package com.ddungja.petmily.sse;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseConnectionPool implements ConnectionPool<String, UserSseConnection> {
    private static final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(String key, UserSseConnection session) {
        connectionPool.put(key, session);

    }

    @Override
    public UserSseConnection getSession(String uniqueKey) {
        if (connectionPool.isEmpty()) {
            throw new RuntimeException("연결된 세션이 없습니다.");
        }
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSseConnection session) {
        connectionPool.remove(session.getUniqueKey());
    }
}
