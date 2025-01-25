package com.postech.gerencie.pedidos.usecase.dto;

import com.postech.gerencie.pedidos.domain.QuantidadeItem;
import com.postech.gerencie.pedidos.domain.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Collection;

public record PedidoDTO(
        String cpfCliente,
        StatusPedido status,
        String descricao,
        Collection<QuantidadeItem> itens,
        String cupomAplicado,
        Double valorTotal,
        String logradouroEntrega,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
