package com.postech.gerencie.pedidos.usecase.dto.novopedido;

import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public record NovoPedidoDTO (
        String cpfCliente,
        Collection<QuantidadeItemDTO> quantidadeItemDTOS,
        String cupomAplicado,
        String cepEntrega
) {

    public Map<Long, Double> getItemQuantidadeMap() {
        return this.quantidadeItemDTOS
                .stream()
                .collect(Collectors.toMap(QuantidadeItemDTO::itemId, QuantidadeItemDTO::quantidade));
    }
}
