package com.postech.gerencie.pedidos.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CupomTest {

    private final static Double valorTotal = 1000d;

    @Test
    void descontarSemLimite() {
        Cupom secretoSemLimite = new Cupom("SECRETO", 0.1);

        var valorComDesconto = secretoSemLimite.descontar(valorTotal);
        Assertions.assertEquals(900, valorComDesconto);
    }

    @Test
    void descontarComLimite() {
        Cupom secretoComLimite = new Cupom("SECRETO", 0.1,50d);

        var valorComDesconto = secretoComLimite.descontar(valorTotal);
        Assertions.assertEquals(950, valorComDesconto);
    }
}