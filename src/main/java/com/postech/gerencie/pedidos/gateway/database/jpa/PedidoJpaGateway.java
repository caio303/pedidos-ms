package com.postech.gerencie.pedidos.gateway.database.jpa;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import org.springframework.stereotype.Component;

@Component
public class PedidoJpaGateway implements PedidoGateway {

    @Override
    public void registrarAberturaPedido(PedidoDTO pedidoDTO) {

    }

    @Override
    public void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus) {
        atualizarStatusPedido(pedidoId, novoStatus, null);
    }

    @Override
    public void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus, String novaDescricao) {

    }

}
