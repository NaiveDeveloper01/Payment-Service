package com.payment.gateway.models.responses.payment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private String transactionID;
    private String transactionStatus;
}
