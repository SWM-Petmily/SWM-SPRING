package com.ddungja.petmily.common.infra;

import com.ddungja.petmily.common.service.ExpireTimeHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SystemExpireTimeHolder implements ExpireTimeHolder {
    @Override
    public LocalDateTime millis() {
        return LocalDateTime.now().plusMinutes(3);
    }
}
