package com.postech.gerencie.pedidos.gateway.database.jpa;

import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import org.springframework.stereotype.Component;

@Component
public class CupomJpaGateway implements CupomGateway {
    @Override
    public void criarCupom(String chave, float porcentagemOff, Float limiteDesconto) {

    }

    @Override
    public void excluirCupom(String chave) {

    }

    @Override
    public Cupom buscarPorChave(String chaveCupom) {
        return new Cupom("SECRETO", 0.20d, 15d);
    }
}
