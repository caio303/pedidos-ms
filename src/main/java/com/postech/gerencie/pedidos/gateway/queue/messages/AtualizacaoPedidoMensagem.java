package com.postech.gerencie.pedidos.gateway.queue.messages;

public record AtualizacaoPedidoMensagem (
        long pedidoId,
        int novoStatusId,
        String codigoEntrega
) {
}
