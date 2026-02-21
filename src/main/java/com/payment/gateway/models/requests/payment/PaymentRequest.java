package com.payment.gateway.models.requests.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Double amount;
    private String accountNumber;
    private String rec;
}
