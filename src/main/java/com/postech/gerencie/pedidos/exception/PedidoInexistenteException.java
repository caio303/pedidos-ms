package com.postech.gerencie.pedidos.exception;

public class PedidoInexistenteException extends RuntimeException {
    public PedidoInexistenteException(long pedidoId) {
        super("Pedido inexistente: " + pedidoId);
    }
}
