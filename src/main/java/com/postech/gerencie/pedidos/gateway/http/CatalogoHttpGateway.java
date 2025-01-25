package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.domain.Item;
import com.postech.gerencie.pedidos.gateway.CatalogoGateway;

import java.util.Collection;
import java.util.List;

public class CatalogoHttpGateway implements CatalogoGateway {

    // criar projetinho mockado pra testar a comunicacao

    @Override
    public Collection<Item> listarPorIds(Collection<Long> itemIds) {
        return List.of();
    }


}
