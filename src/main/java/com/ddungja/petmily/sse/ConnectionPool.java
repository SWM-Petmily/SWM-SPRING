package com.ddungja.petmily.sse;

public interface ConnectionPool<T , R> {
    void addSession(T key, R session);


    R getSession(T uniqueKey);

    void onCompletionCallback(R session);


}
