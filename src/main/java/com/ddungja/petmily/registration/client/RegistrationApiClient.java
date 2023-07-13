package com.ddungja.petmily.registration.client;

import com.ddungja.petmily.registration.controller.response.RegistrationApiResponse;
import com.ddungja.petmily.registration.domain.RegistrationApiItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "registration-api", url = "https://apis.data.go.kr/1543061/animalInfoSrvc", configuration = RegistrationConfig.class)
public interface RegistrationApiClient {
    @RequestMapping(method = RequestMethod.GET, value = "/animalInfo")
    RegistrationApiResponse getAnimalInfo(@RequestParam MultiValueMap<String, String> requestParam);
}
