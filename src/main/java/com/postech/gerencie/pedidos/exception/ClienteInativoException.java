package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class ClienteInativoException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    public ClienteInativoException(String cpf) {
        super("Cliente Inativo: " + cpf);
    }
}
