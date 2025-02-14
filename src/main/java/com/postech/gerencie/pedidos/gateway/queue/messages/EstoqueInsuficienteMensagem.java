package com.postech.gerencie.pedidos.gateway.queue.messages;

public record EstoqueInsuficienteMensagem (
        long pedidoId
){
}
