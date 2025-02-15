package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.domain.Pedido;
import com.postech.gerencie.pedidos.domain.validator.ValidadorCpf;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPedidosPorClienteUseCase {

    private final PedidoGateway pedidoGateway;

    public ListarPedidosPorClienteUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public List<Pedido> listarPorCpf(String cpf) {
        var validadorCpf = new ValidadorCpf();
        if (!validadorCpf.ehCPFValido(cpf)) {
            throw new FormatoInvalidoException("cpf", cpf);
        }

        return pedidoGateway.listarPorCpf(cpf);
    }
}
