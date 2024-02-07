package com.ddungja.petmily.common.log;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 진입전
        log.info(">>>>> 진입 ");

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        var reqJson = new String(req.getContentAsByteArray());
        log.info("request : {}", reqJson);

        var resJson = new String(res.getContentAsByteArray());
        log.info("response : {}", resJson);


        log.info("<<<<< 리턴");
        // 진입후

        res.copyBodyToResponse();
    }
}
