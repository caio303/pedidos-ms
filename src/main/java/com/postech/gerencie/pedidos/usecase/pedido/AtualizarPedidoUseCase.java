package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.exception.StatusInexistenteException;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public AtualizarPedidoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void atualizarPedido(long pedidoId, int novoStatus, String codigoEntrega) {
        StatusPedido statusPedido = StatusPedido.deId(novoStatus);

        if (statusPedido == null) {
            throw new StatusInexistenteException(novoStatus);
        }

        pedidoGateway.atualizarStatusPedido(pedidoId, statusPedido, codigoEntrega);
    }

}
