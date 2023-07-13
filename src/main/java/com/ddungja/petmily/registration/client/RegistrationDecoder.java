package com.ddungja.petmily.registration.client;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class RegistrationDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        if(response.status() == 200) {
            log.info("반려동물 API 응답 200 정상");
            log.info(response.reason());

        }else{
            log.error("RegistrationDecoder.decode() response.status() : {}", response.status());
        }
        return new GsonDecoder().decode(response, type);
    }

}
