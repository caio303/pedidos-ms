package com.postech.gerencie.pedidos.usecase.cupom;

import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ExcluirCupomUseCaseTest {

    private ExcluirCupomUseCase subject;
    private CupomGateway cupomGateway;

    @BeforeEach
    void setUp() {
        this.cupomGateway = mock(CupomGateway.class);
        subject = new ExcluirCupomUseCase(cupomGateway);
    }

    @Test
    void excluiComSucessoParaChaveValida() {
        var chaveValida = "SECRETO";
        subject.excluirCupom(chaveValida);

        verify(cupomGateway, times(1)).excluirCupom(chaveValida);
    }

    @Test
    void jogaErroParaChaveInvalida() {
        var chaveInvalida = "";

        assertThrows(FormatoInvalidoException.class, () -> subject.excluirCupom(chaveInvalida));
    }
}