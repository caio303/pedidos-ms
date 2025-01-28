package com.postech.gerencie.pedidos.gateway.queue.messages;

import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;

import java.util.Collection;

public record NovoPedidoMensagem(
        String cpfCliente,
        Collection<QuantidadeItemDTO> itens,
        String cepEntrega
) { }
