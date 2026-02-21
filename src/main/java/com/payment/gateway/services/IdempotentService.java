package com.payment.gateway.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payment.gateway.models.responses.payment.TransactionResponse;

public interface IdempotentService {
    boolean isExist(String idempotentKey);

    String generate(String key);

    void saveKey(String idempotentKey, TransactionResponse transactionResponse);

    String getResponse(String key);
}
