package com.ddungja.petmily.common.infra;

import com.ddungja.petmily.common.service.ClockHolder;

import java.time.LocalDateTime;

public class SystemClockHolder implements ClockHolder {
    @Override
    public LocalDateTime expireAt() {
        return LocalDateTime.now().plusMinutes(3);
    }
}
