package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.gateway.CatalogoGateway;
import com.postech.gerencie.pedidos.usecase.dto.ItemComIdDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CatalogoHttpGateway implements CatalogoGateway {

    // criar projetinho mockado pra testar a comunicacao

    @Override
    public void cadastrarItem(Long id) {

    }

    @Override
    public Collection<ItemComIdDTO> listarPorIds(Collection<Long> itemIds) {
        return List.of(new ItemComIdDTO(1l, "teste", 100d));
    }


}
