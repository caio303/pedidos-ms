package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class CupomInexistenteException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public CupomInexistenteException(String chave) {
        super("Cupom inexistente: " + chave);
    }
}
