//package com.ddungja.app.global.jwt;
//
//import com.ddungja.app.user.domain.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gongguiljeong.domain.admin.dto.AdminLoginRequest;
//import com.gongguiljeong.domain.admin.model.Admin;
//import com.gongguiljeong.global.util.CustomResponse;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.time.Duration;
//
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtProvider jwtProvider;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
//        super(authenticationManager);
//        this.jwtProvider = jwtProvider;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            User user = objectMapper.readValue(request.getInputStream(), User.class);
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), adminLoginRequest.getPassword());
//            return authenticationManager.authenticate(token);
//        } catch (IOException e) {
//            throw new InternalAuthenticationServiceException(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
//        CustomResponse.unAuthentication(response, "로그인 실패 ");
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
//        Admin admin = (Admin) authResult.getPrincipal();
//        String jwtToken = jwtProvider.createAccessToken(admin);
//        String refreshToken = jwtProvider.createRefreshToken(admin);
//        response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
//        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
//                .maxAge(Duration.ofDays(14))
//                .path("/")
////                .secure(true) //https를 쓸때 사용
//                .sameSite("None") //csrf 공격을 방지하기 위해 설정
////                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
//                .httpOnly(true)
//                .build();
//        response.setHeader("Set-Cookie", cookie.toString());
//        AdminLoginRequest adminLoginRequest = new AdminLoginRequest(admin);
//        CustomResponse.success(response, adminLoginRequest);
//
//    }
//}
