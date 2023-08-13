package com.ddungja.petmily.mock;

import com.ddungja.petmily.common.service.ClockHolder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TestClockHolder implements ClockHolder {

    private final int minutes;

    @Override
    public LocalDateTime expireAt() {
        return LocalDateTime.now().plusMinutes(minutes);
    }
}
