package com.ddungja.petmily.registration.client;

import feign.Logger;
import feign.codec.Decoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.Encoder;

@Configuration
public class RegistrationConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    Decoder feignFormDecoder() {
        return new RegistrationDecoder();
    }

}
