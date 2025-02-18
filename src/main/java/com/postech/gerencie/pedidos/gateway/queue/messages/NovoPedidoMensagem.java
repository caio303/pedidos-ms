package com.postech.gerencie.pedidos.gateway.queue.messages;

import java.util.Map;

public record NovoPedidoMensagem(
        Long pedidoId,
        String cpfCliente,
        Map<Long, Double> itens,
        String cepEntrega
) { }
