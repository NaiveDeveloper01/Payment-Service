package com.payment.gateway.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonUtils {
    private final ObjectMapper objectMapper;

    public <T> String getJson(T val) {
        try {
            return objectMapper.writeValueAsString(val);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public <U> U deserializeValue(String s, Class<U> u) {
        try {
            return objectMapper.readValue(s, u);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
