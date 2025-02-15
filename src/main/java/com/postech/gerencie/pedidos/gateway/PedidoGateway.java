package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.Pedido;
import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;

import java.util.List;

public interface PedidoGateway {

    void registrarAberturaPedido(PedidoDTO pedidoDTO);

    void atualizarStatusPedido(long pedidoId, StatusPedido novoStatus, String novaDescricao);

    List<Pedido> listarPorCpf(String cpf);
}
