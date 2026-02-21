package com.payment.gateway.services;

import com.payment.gateway.models.requests.payment.PaymentRequest;
import com.payment.gateway.models.responses.payment.TransactionResponse;

public interface PaymentService {
    TransactionResponse transactionResponse(String requestKey, PaymentRequest paymentRequest);
}
