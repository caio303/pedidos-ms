package com.postech.gerencie.pedidos.gateway.http.externalapi;

import com.postech.gerencie.pedidos.usecase.dto.ItemComIdDTO;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CatalogoExternalApi {

    private static final Logger log = LoggerFactory.getLogger(CatalogoExternalApi.class);

    @Value("${pedidos.catalogo-service.baseurl}")
    private String catalogoServiceBaseUrl;

    private HttpFacade httpFacade;

    @PostConstruct
    void setUp() {
        httpFacade =  new HttpFacade(catalogoServiceBaseUrl);
    }

    public Collection<ItemComIdDTO> buscarPorIds (Collection<Long> itemIds) {
        log.debug("Buscou itens: {}", itemIds);
        var params = Map.of("ids",itemIds.toArray());
        var itens = httpFacade.get("/produto", params, Collection.class);

        return itens
                .stream()
                .map(map -> new ItemComIdDTO((LinkedHashMap<String, Object>) map))
                .toList();
    }

}
