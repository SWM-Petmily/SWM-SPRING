package com.ddungja.petmily.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ddungja.petmily.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;


@Component
public class JwtProvider {


    @Value("${jwt.access.secret}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${jwt.refresh.secret}")
    private String REFRESH_TOKEN_SECRET_KEY;
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofDays(14).toMillis();
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(30).toMillis();
    public static final String PREFIX = "Bearer ";

    public String createAccessToken(User user) {
        String accessToken = JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY));
        return PREFIX + accessToken;
    }
    public String createTestAccessToken(Long userId) {
        String accessToken = JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("id", userId)
                .sign(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY));
        return PREFIX + accessToken;
    }
    public String createRefreshToken(User user) {
        return JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY));
    }

    public User accessTokenVerify(String accessToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(accessToken);
        Long id = decodedJWT.getClaim("id").asLong();
        return User.builder()
                .id(id)
                .build();
    }

    public User refreshTokenVerify(String refreshToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();
        return User.builder()
                .id(id)
                .build();
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(accessToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(refreshToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
