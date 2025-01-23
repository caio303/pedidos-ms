package com.postech.gerencie.pedidos.domain;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Collection;

public record Pedido(
        String cpfCliente,
        StatusPedido status,
        String descricao,
        Collection<ItemPedido> itens,
        String cupomAplicado,
        String logradouroEntrega,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) { }
