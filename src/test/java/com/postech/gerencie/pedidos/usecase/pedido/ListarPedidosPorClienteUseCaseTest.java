package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ListarPedidosPorClienteUseCaseTest {


    private PedidoGateway pedidoGateway;
    private ListarPedidosPorClienteUseCase subject;

    private final String cpfValido = "24658714043";

    @BeforeEach
    void setUp() {
        this.pedidoGateway = mock(PedidoGateway.class);
        this.subject = new ListarPedidosPorClienteUseCase(pedidoGateway);
    }

    @Test
    void listarPorCpfValido() {
        subject.listarPorCpf(cpfValido);

        verify(pedidoGateway, times(1)).listarPorCpf(cpfValido);
    }

    @Test
    void listarPorCpfInvalido() {
        var invalido = "246587";

        assertThrows(FormatoInvalidoException.class, () -> subject.listarPorCpf(invalido));
    }

}