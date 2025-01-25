package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.domain.Item;

import java.util.Collection;

public interface CatalogoGateway {

    Collection<Item> listarPorIds(Collection<Long> itemIds);

}
