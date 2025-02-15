package com.postech.gerencie.pedidos.exception;

import org.springframework.http.HttpStatus;

public class PedidoSemItensException extends BaseHttpMappedException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public PedidoSemItensException() {
        super("Pedido deve ter ao menos um item");
    }
}
