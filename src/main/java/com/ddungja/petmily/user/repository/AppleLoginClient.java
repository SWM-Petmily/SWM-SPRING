package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.apple.ApplePublicKeys;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apple-public-key-client", url = "https://appleid.apple.com/auth")
public interface AppleLoginClient {
 
    @GetMapping("/keys")
    ApplePublicKeys getApplePublicKeys();
}