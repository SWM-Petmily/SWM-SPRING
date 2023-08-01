package com.ddungja.petmily.user.domain.apple;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class ApplePublicKey {
 
    private String kty;
    private String kid;
    private String use;
    private String alg;
    private String n;
    private String e;
}
