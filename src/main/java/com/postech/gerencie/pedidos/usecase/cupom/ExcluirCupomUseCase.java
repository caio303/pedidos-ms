package com.postech.gerencie.pedidos.usecase.cupom;

import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ExcluirCupomUseCase {

    private final CupomGateway cupomGateway;

    public ExcluirCupomUseCase(CupomGateway cupomGateway) {
        this.cupomGateway = cupomGateway;
    }

    @Transactional
    public void excluirCupom(String chave) {
        if (chave == null || chave.isBlank()) {
            throw new FormatoInvalidoException("chave", chave);
        }

        cupomGateway.excluirCupom(chave);
    }


}
