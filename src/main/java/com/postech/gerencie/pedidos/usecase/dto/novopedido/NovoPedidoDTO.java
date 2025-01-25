package com.postech.gerencie.pedidos.usecase.dto.novopedido;

import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;

import java.util.Collection;

public record NovoPedidoDTO (
        String cpfCliente,
        Collection<QuantidadeItemDTO> itemIds,
        String cupomAplicado,
        String cepEntrega
) { }
