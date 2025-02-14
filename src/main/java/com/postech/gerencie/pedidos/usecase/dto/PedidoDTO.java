package com.postech.gerencie.pedidos.usecase.dto;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Collection;

public record PedidoDTO(
        String cpfCliente,
        StatusPedido status,
        Collection<QuantidadeItemDTO> itens,
        String cupomAplicado,
        Double valorTotal,
        String cepEntrega,
        String codigoRastreio,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
