package com.postech.gerencie.pedidos.gateway.database.jpa.mapper;

import com.postech.gerencie.pedidos.domain.Cupom;

public class CupomMapper {

    public Cupom toDomain(com.postech.gerencie.pedidos.gateway.database.jpa.entities.Cupom cupom) {
        return new Cupom(cupom.getChave(), cupom.getPorcentagemOff(), cupom.getLimiteDesconto());
    }

    public com.postech.gerencie.pedidos.gateway.database.jpa.entities.Cupom toEntity(Cupom cupomDomain) {
        return new com.postech.gerencie.pedidos.gateway.database.jpa.entities.Cupom(
                null,
                cupomDomain.getChave(),
                cupomDomain.getPorcentagemOff(),
                cupomDomain.getLimiteDesconto()
        );
    }
}
