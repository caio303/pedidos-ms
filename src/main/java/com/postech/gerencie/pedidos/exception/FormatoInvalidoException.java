package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class FormatoInvalidoException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public FormatoInvalidoException(String campo, Object valor) {
        super("Formatação inválida para o campo '" + campo + "', valor: " + valor);
    }
}
