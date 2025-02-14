package com.postech.gerencie.pedidos.gateway.queue.messages;

import java.util.Map;

public record NovoPedidoMensagem(
        String cpfCliente,
        Map<Long, Double> itens,
        String cepEntrega
) { }
