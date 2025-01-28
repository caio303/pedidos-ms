package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.Cupom;

public interface CupomGateway {

    void criarCupom(String chave, float porcentagemOff, Float limiteDesconto);

    void excluirCupom(String chave);

    Cupom buscarPorChave(String chaveCupom);

}
