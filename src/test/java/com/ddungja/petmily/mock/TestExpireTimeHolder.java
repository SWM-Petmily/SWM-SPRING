package com.ddungja.petmily.mock;

import com.ddungja.petmily.common.service.ExpireTimeHolder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@RequiredArgsConstructor
public class TestExpireTimeHolder implements ExpireTimeHolder {

    private final int minutes;

    @Override
    public LocalDateTime millis() {
        return LocalDateTime.now().plusMinutes(minutes);
    }
}
