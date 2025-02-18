package com.postech.gerencie.pedidos.usecase.cupom;

import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.exception.CupomJaCadastradoException;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.usecase.dto.NovoCupomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarCupomUseCaseTest {

    private CriarCupomUseCase subject;

    private CupomGateway cupomGateway;

    private final String chaveValida = "SECRETO";
    private final NovoCupomDTO novoCupomDTO = new NovoCupomDTO(chaveValida, 0.1, null);
    private final Cupom cupomDomain = new Cupom(chaveValida, 0.1);

    @BeforeEach
    void setUp() {
        this.cupomGateway = mock(CupomGateway.class);
        this.subject = new CriarCupomUseCase(cupomGateway);
    }

    @Test
    void criaCupomComSucesso() {
        subject.criarCupom(novoCupomDTO);

        when(cupomGateway.buscarPorChave(chaveValida)).thenReturn(null);

        verify(cupomGateway, times(1)).criarCupom(any(Cupom.class));
    }

    @Test
    void naoCriaCupomDuplicado() {
        when(cupomGateway.buscarPorChave(chaveValida)).thenReturn(cupomDomain);

        assertThrows(CupomJaCadastradoException.class, () -> subject.criarCupom(novoCupomDTO));
    }

    @Test
    void naoCriaSemChave() {
        assertThrows(FormatoInvalidoException.class, () -> subject.criarCupom(new NovoCupomDTO(null, 0.1, null)));
    }
}