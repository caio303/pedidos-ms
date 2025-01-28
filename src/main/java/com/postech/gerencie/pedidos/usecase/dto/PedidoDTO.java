package com.postech.gerencie.pedidos.usecase.dto;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Collection;

public record PedidoDTO(
        String cpfCliente,
        StatusPedido status,
        String descricao,
        Collection<QuantidadeItemDTO> itens,
        String cupomAplicado,
        Double valorTotal,
        String cepEntrega,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
