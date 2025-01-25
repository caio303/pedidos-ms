package com.postech.gerencie.pedidos.gateway.stream.messages;

import com.postech.gerencie.pedidos.domain.QuantidadeItem;

import java.util.Collection;

public record NovoPedidoMensagem(
        String cpfCliente,
        Collection<QuantidadeItem> itemIds,
        String cepEntrega
) { }
