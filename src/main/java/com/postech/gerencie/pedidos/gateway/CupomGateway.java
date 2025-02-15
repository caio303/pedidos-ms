package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.Cupom;

public interface CupomGateway {

    void criarCupom(Cupom cupom);

    void excluirCupom(String chave);

    Cupom buscarPorChave(String chaveCupom);

    Long buscarIdPorChave(String chaveCupom);

}
