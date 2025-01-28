package com.postech.gerencie.pedidos.domain;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Collection;

public record Pedido(
        String cpfCliente,
        StatusPedido status,
        String descricao,
        Collection<QuantidadeItem> itens,
        String cupomAplicado,
        Double valorTotal,
        String cepEntrega,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) { }
