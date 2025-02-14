package com.postech.gerencie.pedidos.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StatusPedidoTest {

    @Test
    void retornarCorretoPorId() {
        int emRota = 3;
        StatusPedido statusPedido = StatusPedido.deId(emRota);

        assertEquals(StatusPedido.EM_ROTA, statusPedido);
    }

    @Test
    void retornarNuloPraIdInvalido() {
        int invalido = 666;
        StatusPedido statusPedido = StatusPedido.deId(invalido);

        assertNull(statusPedido);
    }
}