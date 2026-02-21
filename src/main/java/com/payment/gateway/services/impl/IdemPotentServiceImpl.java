package com.payment.gateway.services.impl;

import com.payment.gateway.models.responses.payment.TransactionResponse;
import com.payment.gateway.services.IdempotentService;
import com.payment.gateway.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.payment.gateway.constants.AppConstants.IDEMPOTENT_PREFIX;

@Service
@RequiredArgsConstructor
public class IdemPotentServiceImpl implements IdempotentService {
    private final StringRedisTemplate stringRedisTemplate;
    private final JsonUtils jsonUtils;

    @Override
    public boolean isExist(String key) {
        return stringRedisTemplate.hasKey(generate(key));
    }

    @Override
    public String generate(String key) {
        return IDEMPOTENT_PREFIX + key;
    }

    @Override
    public void saveKey(String key, TransactionResponse transactionResponse) {
        String idempotentKey = generate(key);
        stringRedisTemplate.opsForValue()
                .set(idempotentKey, jsonUtils.getJson(transactionResponse), Duration.ofHours(24));
    }

    @Override
    public String getResponse(String key) {
        return stringRedisTemplate.opsForValue().get(generate(key));
    }
}
