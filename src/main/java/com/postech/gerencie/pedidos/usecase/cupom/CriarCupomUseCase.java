package com.postech.gerencie.pedidos.usecase.cupom;

import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.exception.CupomJaCadastradoException;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.usecase.dto.NovoCupomDTO;
import org.springframework.stereotype.Service;

@Service
public class CriarCupomUseCase {

    private final CupomGateway cupomGateway;

    public CriarCupomUseCase(CupomGateway cupomGateway) {
        this.cupomGateway = cupomGateway;
    }

    public void criarCupom(NovoCupomDTO novoCupomDTO) {
        var chaveNovoCupom = novoCupomDTO.chave();
        var porcentagemOff = novoCupomDTO.porcentagemOff();
        var limiteDesconto = novoCupomDTO.limiteDesconto();

        if (chaveNovoCupom == null) {
            throw new FormatoInvalidoException("chave", null);
        }

        var cupomExistente = cupomGateway.buscarPorChave(chaveNovoCupom);
        if (cupomExistente != null) {
            throw new CupomJaCadastradoException(chaveNovoCupom);
        }

        var cupom = new Cupom(chaveNovoCupom, porcentagemOff, limiteDesconto);

        cupomGateway.criarCupom(cupom);
    }


}
