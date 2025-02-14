package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPedidosPorClienteUseCase {

    private final PedidoGateway pedidoGateway;

    public ListarPedidosPorClienteUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public List<Object> listarPorCpf(String cpf) {
        pedidoGateway.listarPorCpf(cpf);
        return List.of();
    }
}
