package com.ddungja.petmily.registration.controller.response;

import com.ddungja.petmily.registration.domain.RegistrationApiItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
