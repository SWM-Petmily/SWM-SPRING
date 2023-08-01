package com.ddungja.petmily.user.domain.apple;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AppleClientTest {

    @Autowired
    private AppleClient appleClient;
    @Autowired
    private AppleJwtParser appleJwtParser;
    @Autowired
    private PublicKeyGenerator publicKeyGenerator;

    @Test
    @DisplayName("apple 서버와 통신하여 Apple public keys 응답을 받는다")
    void getPublicKeys() {
        ApplePublicKeys applePublicKeys = appleClient.getApplePublicKeys();
        List<ApplePublicKey> keys = applePublicKeys.getKeys();

    }

    @Test
    @DisplayName("identity token 값 테스트")
    void test2() {
        Map<String, String> stringStringMap = appleJwtParser.parseHeaders("eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoib3JnLnNvbWEuRGR1bmdqYSIsImV4cCI6MTY5MDg5MzA3MiwiaWF0IjoxNjkwODA2NjcyLCJzdWIiOiIwMDE3NzcuZmQ1YTdkMjVkZDAzNDc4OWJkMzQ5MGJkOGY1NjRjMDMuMDAzMiIsImNfaGFzaCI6IjBQNWxmdUpJaXpEeUlUck9URXRrZXciLCJlbWFpbCI6Ijd2NWZ3cnptenRAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2OTA4MDY2NzIsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.sZ7sgPBiO1FumtGd38pKJkIxQ4Ns9YRRjfaXCCAj_ZNoxsYrR6IrNTryDZvHZ1IPmh2XUxNhUTv1kOv9n44RtAh-4AZhO3OEnqes9XcvGp-o9OwhuavcnEODeFeFSgDldTCp_FUcFkhsXuYcArPo2zDB1568Ud9KC_u4vGM6MQ-A2RG9uUEu331pHQyzRJEH92Qyk__RX93Sn-N90SCxuC--qpINgcykmbqXuiqbRhbfPiHkQYxLpZsTxZXeexLQy3XcH1wq9EpN-CzMRue7gjVI3GwtpJMJb3otDkk10apUsq8n5P8z28MN98Gv5Y-61GG6V1ZCWM9MveouxBdg3A");
        for (String key : stringStringMap.keySet()) {
            System.out.println("key = " + key);
            System.out.println("value = " + stringStringMap.get(key));
        }
    }

    @Test
    @DisplayName("공개키 얻기")
    public void test3(){
        ApplePublicKeys applePublicKeys = appleClient.getApplePublicKeys();
        System.out.println("applePublicKeys = " + applePublicKeys);
        Map<String, String> stringStringMap = appleJwtParser.parseHeaders("eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoib3JnLnNvbWEuRGR1bmdqYSIsImV4cCI6MTY5MDg3Mjg0MywiaWF0IjoxNjkwNzg2NDQzLCJzdWIiOiIwMDE3NzcuZmQ1YTdkMjVkZDAzNDc4OWJkMzQ5MGJkOGY1NjRjMDMuMDAzMiIsImNfaGFzaCI6IlV1RE4tQ1dpTm43RWV0T0dOQ3BEa0EiLCJlbWFpbCI6Ijd2NWZ3cnptenRAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2OTA3ODY0NDMsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.0qLJa88FVxYkQ-RCiayJOlM4RnvnsNTFIc-8n8AH1DOLMDgaoleIg5bXo_oo1mdHgVe-CS9pZmNRjn1PjH_YkbB_zT0l4pYoNxgI196nhq6MaYi5sI3zEBs_A0isOmI9G8lPkIepP7CLJmaguqIXPtLrNvXNSjxRnnLgxMxuty8jLvWBoRFG9hyvLmFFsesfcDdecuZFzaFBu1UBTez6vqh2-eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoib3JnLnNvbWEuRGR1bmdqYSIsImV4cCI6MTY5MDg5MzA3MiwiaWF0IjoxNjkwODA2NjcyLCJzdWIiOiIwMDE3NzcuZmQ1YTdkMjVkZDAzNDc4OWJkMzQ5MGJkOGY1NjRjMDMuMDAzMiIsImNfaGFzaCI6IjBQNWxmdUpJaXpEeUlUck9URXRrZXciLCJlbWFpbCI6Ijd2NWZ3cnptenRAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2OTA4MDY2NzIsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.sZ7sgPBiO1FumtGd38pKJkIxQ4Ns9YRRjfaXCCAj_ZNoxsYrR6IrNTryDZvHZ1IPmh2XUxNhUTv1kOv9n44RtAh-4AZhO3OEnqes9XcvGp-o9OwhuavcnEODeFeFSgDldTCp_FUcFkhsXuYcArPo2zDB1568Ud9KC_u4vGM6MQ-A2RG9uUEu331pHQyzRJEH92Qyk__RX93Sn-N90SCxuC--qpINgcykmbqXuiqbRhbfPiHkQYxLpZsTxZXeexLQy3XcH1wq9EpN-CzMRue7gjVI3GwtpJMJb3otDkk10apUsq8n5P8z28MN98Gv5Y-61GG6V1ZCWM9MveouxBdg3A");
        PublicKey publicKey = publicKeyGenerator.generatePublicKey(stringStringMap, applePublicKeys);
        System.out.println("publicKey = " + publicKey);
    }
}
