package com.ddungja.petmily.registration.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Getter
@NoArgsConstructor
public class MyRegistrationsResponse {
    private List<MyRegistrationResponse> content;
    @Builder
    public MyRegistrationsResponse(List<MyRegistrationResponse> content) {
        this.content = content;
    }

    public static MyRegistrationsResponse from(Stream<MyRegistrationResponse> myRegistrationResponseStream) {
        return MyRegistrationsResponse.builder()
                .content(myRegistrationResponseStream.toList())
                .build();
    }
}
