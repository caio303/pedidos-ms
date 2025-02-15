package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class CupomJaCadastradoException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }

    public CupomJaCadastradoException(String chave) {
        super("Cupom ja cadastrado: " + chave);
    }
}
