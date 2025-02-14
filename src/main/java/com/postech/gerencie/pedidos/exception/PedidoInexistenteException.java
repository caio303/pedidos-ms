package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class PedidoInexistenteException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public PedidoInexistenteException(long pedidoId) {
        super("Pedido inexistente: " + pedidoId);
    }
}
