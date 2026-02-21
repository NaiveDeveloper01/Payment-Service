package com.payment.gateway.exceptions;

import com.payment.gateway.exceptions.utils.GenericErrorResponse;
import com.payment.gateway.models.responses.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception e) {
        return ResponseEntity.ok(
                GenericResponse
                        .builder()
                        .status("500")
                        .message("Internal server error")
                        .data(setErrorResponse(e))
                        .build()

        );

    }

    private GenericErrorResponse setErrorResponse(Exception e) {
        return GenericErrorResponse.builder()
                .description(e.getLocalizedMessage())
                .message(e.getMessage())
                .build();
    }
}
