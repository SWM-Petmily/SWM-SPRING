package com.ddungja.petmily.user.domain.apple;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class OAuthPlatformMemberResponse {
    private String platformId;
    private String email;
}