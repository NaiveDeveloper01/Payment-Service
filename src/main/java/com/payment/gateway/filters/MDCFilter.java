package com.payment.gateway.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static com.payment.gateway.constants.AppConstants.REQUEST_UUID;

public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        MDC.put(REQUEST_UUID, UUID.randomUUID().toString());
        request.setAttribute(REQUEST_UUID, MDC.get(REQUEST_UUID));
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }

    }
}
