package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class ClienteInexistenteException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    public ClienteInexistenteException(String cpf) {
        super("Cliente não cadastrado: " + cpf);
    }
}
