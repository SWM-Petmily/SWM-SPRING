package com.ddungja.petmily.registration.controller.response;

import com.auth0.jwt.interfaces.Header;
import com.ddungja.petmily.registration.domain.RegistrationApiItem;
import feign.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationApiResponse {
    private Response response;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
       private Items body;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Items{
        private RegistrationApiItem item;
    }

}
