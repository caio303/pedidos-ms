package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;

public interface PedidoGateway {

    void registrarAberturaPedido();

    void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus);
    void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus, String novaDescricao);

}
