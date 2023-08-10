package com.ddungja.petmily.user.domain.apple;

import com.ddungja.petmily.common.exception.CustomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.ddungja.petmily.common.exception.ExceptionCode.INVALID_APPLE_JWT;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ApplePublicKeys {
 
    private List<ApplePublicKey> keys;
 
    public ApplePublicKey getMatchesKey(String alg, String kid) {
        return this.keys
                .stream()
                .filter(k -> k.getAlg().equals(alg) && k.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new CustomException(INVALID_APPLE_JWT));
    }
}
