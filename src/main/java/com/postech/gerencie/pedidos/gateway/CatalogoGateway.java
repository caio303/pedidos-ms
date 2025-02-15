package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.usecase.dto.ItemComIdDTO;

import java.util.Collection;

public interface CatalogoGateway {

    Collection<ItemComIdDTO> listarPorIds(Collection<Long> itemIds);

}
