package com.ddungja.petmily.registration.repository;

import com.ddungja.petmily.common.config.OpenFeignConfig;
import com.ddungja.petmily.registration.controller.response.RegistrationApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "registration-api", url = "https://apis.data.go.kr/1543061/animalInfoSrvc", configuration = OpenFeignConfig.class)
public interface RegistrationApiClient {
    @GetMapping("/animalInfo")
    RegistrationApiResponse getAnimalInfo(@RequestParam String owner_nm, @RequestParam String dog_reg_no, @RequestParam String serviceKey, @RequestParam String _type);
}
