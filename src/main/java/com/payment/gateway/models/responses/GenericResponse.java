package com.payment.gateway.models.responses;

import lombok.*;

@Getter
@Setter
@Builder
public class GenericResponse<T> {
    private String status;
    private String message;
    private String requestUUID;
    private T data;
}
