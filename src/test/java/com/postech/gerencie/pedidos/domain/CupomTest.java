package com.postech.gerencie.pedidos.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CupomTest {

    private static final Double VALOR_TOTAL = 1000d;

    @Test
    void descontarSemLimite() {
        Cupom secretoSemLimite = new Cupom("SECRETO", 0.1);

        var valorComDesconto = secretoSemLimite.descontar(VALOR_TOTAL);
        Assertions.assertEquals(900, valorComDesconto);
    }

    @Test
    void descontarComLimite() {
        Cupom secretoComLimite = new Cupom("SECRETO", 0.1,50d);

        var valorComDesconto = secretoComLimite.descontar(VALOR_TOTAL);
        Assertions.assertEquals(950, valorComDesconto);
    }

    @Test
    void valorComDescontoNaoPodeSerNegativo() {
        Cupom secretoComPorcentagemAcimaDoValor = new Cupom("SECRETO", 1);

        var valorComDesconto = secretoComPorcentagemAcimaDoValor.descontar(VALOR_TOTAL);
        Assertions.assertEquals(0, valorComDesconto);
    }

    @Test
    void descontarDeValorNegativoRetornaZero() {
        Cupom secretoSemLimite = new Cupom("SECRETO", 0.1);

        var valorComDesconto = secretoSemLimite.descontar(-666d);
        Assertions.assertEquals(0, valorComDesconto);
    }
}