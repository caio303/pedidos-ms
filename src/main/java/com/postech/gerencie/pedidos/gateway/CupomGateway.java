package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.Cupom;

public interface CupomGateway {

    Cupom buscarPorChave(String chaveCupom);

}
