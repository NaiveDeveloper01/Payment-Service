package com.payment.gateway.services.impl;

import com.payment.gateway.models.requests.payment.PaymentRequest;
import com.payment.gateway.models.responses.payment.TransactionResponse;
import com.payment.gateway.services.IdempotentService;
import com.payment.gateway.services.PaymentService;
import com.payment.gateway.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final IdempotentService idempotentService;
    private final JsonUtils jsonUtils;

    @Override
    public TransactionResponse transactionResponse(String requestKey, PaymentRequest paymentRequest) {
        String cachedResponse = idempotentService.getResponse(requestKey);
        if (Objects.nonNull(cachedResponse))
            return jsonUtils.deserializeValue(idempotentService.getResponse(requestKey), TransactionResponse.class);

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .transactionID(UUID.randomUUID().toString())
                .transactionStatus("success")
                .build();

        idempotentService.saveKey(requestKey, transactionResponse);

        return transactionResponse;
    }

}
