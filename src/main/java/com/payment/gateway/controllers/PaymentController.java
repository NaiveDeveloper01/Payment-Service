package com.payment.gateway.controllers;

import com.payment.gateway.models.requests.payment.PaymentRequest;
import com.payment.gateway.models.responses.GenericResponse;
import com.payment.gateway.models.responses.payment.TransactionResponse;
import com.payment.gateway.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.payment.gateway.constants.pathconstants.PaymentControllerConstants.PAYMENT_CONTROLLER_BASE;
import static com.payment.gateway.constants.pathconstants.PaymentControllerConstants.PAYMENT_TRANSACTION;

@RestController
@RequestMapping(path = PAYMENT_CONTROLLER_BASE)
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(path = PAYMENT_TRANSACTION)
    public ResponseEntity<GenericResponse<?>> createPayment(@RequestHeader(value = "requestKey") String requestKey, @RequestBody PaymentRequest paymentRequest) {
        log.info("PaymentController -> createPayment()");
        TransactionResponse transactionResponse = paymentService.transactionResponse(requestKey, paymentRequest);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .data(transactionResponse)
                        .status("transactions success")
                        .message("success")
                        .build()
        );
    }
}
