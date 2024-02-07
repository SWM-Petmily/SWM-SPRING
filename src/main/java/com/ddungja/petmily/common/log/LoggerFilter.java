package com.ddungja.petmily.common.log;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(req, res);

        //request 정보
        Enumeration<String> headerNames = req.getHeaderNames();
        StringBuilder headerValues = new StringBuilder();
        headerNames.asIterator().forEachRemaining(headerName -> headerValues.append(headerName).append(": ").append(req.getHeader(headerName)).append(","));
        String requestBody = new String(req.getContentAsByteArray());
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        log.info(">>>> uri : {}, method: {}, header: {}, body: {}", requestURI, method, headerValues, requestBody);

        //response 정보
        StringBuilder responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> responseHeaderValues.append(headerKey).append(": ").append(res.getHeader(headerKey)).append(","));
        String responseBody = new String(res.getContentAsByteArray());
        log.info("<<<< uri : {}, method: {}, header: {}, body: {}", requestURI, method, responseHeaderValues, responseBody);


        res.copyBodyToResponse();
    }
}
