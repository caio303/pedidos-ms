package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class DescontoInvalidoException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public DescontoInvalidoException(String message) {
        super(message);
    }
}
