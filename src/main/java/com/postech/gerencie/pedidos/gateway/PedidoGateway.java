package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;

public interface PedidoGateway {

    void registrarAberturaPedido(PedidoDTO pedidoDTO);

    void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus);
    void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus, String novaDescricao);

}
