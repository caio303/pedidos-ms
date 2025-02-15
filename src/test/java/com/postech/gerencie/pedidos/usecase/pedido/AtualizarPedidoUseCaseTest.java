package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.exception.StatusInexistenteException;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AtualizarPedidoUseCaseTest {

    private AtualizarPedidoUseCase subject;
    @Spy private PedidoGateway pedidoGateway;

    private final long pedidoId = 1;
    private final String codigoRastreio = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        this.pedidoGateway = mock(PedidoGateway.class);
        this.subject = new AtualizarPedidoUseCase(pedidoGateway);
    }

    @Test
    void atualizaPedidoComStatusValido() {
        var statusValido = StatusPedido.EM_ROTA.getId();

        subject.atualizarPedido(pedidoId, statusValido, codigoRastreio);

        verify(pedidoGateway, times(1)).atualizarStatusPedido(pedidoId, StatusPedido.deId(statusValido), codigoRastreio);
    }

    @Test
    void jogaExcecaoQuandoStatusInvalido() {
        var statusInvalido = 666;

        assertThrows(StatusInexistenteException.class, () -> subject.atualizarPedido(pedidoId, statusInvalido, codigoRastreio));
    }

}