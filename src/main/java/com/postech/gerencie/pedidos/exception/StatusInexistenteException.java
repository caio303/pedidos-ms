package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class StatusInexistenteException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public StatusInexistenteException(int statusId) {
        super("Status inexistente: " + statusId);
    }
}
