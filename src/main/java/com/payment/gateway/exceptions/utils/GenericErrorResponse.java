package com.payment.gateway.exceptions.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericErrorResponse {
    private String message;
    private String description;
}
