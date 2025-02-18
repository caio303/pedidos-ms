package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.gateway.CatalogoGateway;
import com.postech.gerencie.pedidos.gateway.http.externalapi.CatalogoExternalApi;
import com.postech.gerencie.pedidos.usecase.dto.ItemComIdDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CatalogoHttpGateway implements CatalogoGateway {

    private final CatalogoExternalApi catalogoExternalApi;

    public CatalogoHttpGateway(CatalogoExternalApi catalogoExternalApi) {
        this.catalogoExternalApi = catalogoExternalApi;
    }

    @Override
    public Collection<ItemComIdDTO> listarPorIds(Collection<Long> itemIds) {
        return catalogoExternalApi.buscarPorIds(itemIds);
    }


}
