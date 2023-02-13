package com.LilGonzz.sicredappvotation.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomRequestInterceptor extends WebRequestHandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    public CustomRequestInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String log = "\n\nRequisição solicitada" +
                "\nMétodo: " + request.getMethod() +
                "\nURL: " + request.getRequestURL().toString() +
                "\nTimestamp: " + LocalDateTime.now();

        logger.info(log);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        String log = "\n\nRequisição finalizada" +
                "\nStatus: " + response.getStatus() +
                "\nTimestamp: " + LocalDateTime.now();
        logger.info(log);
    }
}