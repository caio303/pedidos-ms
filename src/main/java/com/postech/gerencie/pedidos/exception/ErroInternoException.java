package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class ErroInternoException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ErroInternoException(String message) {
        super(message);
    }
}
