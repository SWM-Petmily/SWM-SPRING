package com.ddungja.petmily.user.domain.apple;

import lombok.*;

@Getter
public class ApplePublicKey {
    private final String kty;
    private final String kid;
    private final String use;
    private final String alg;
    private final String n;
    private final String e;

    @Builder
    private ApplePublicKey(String kty, String kid, String use, String alg, String n, String e) {
        this.kty = kty;
        this.kid = kid;
        this.use = use;
        this.alg = alg;
        this.n = n;
        this.e = e;
    }
}
