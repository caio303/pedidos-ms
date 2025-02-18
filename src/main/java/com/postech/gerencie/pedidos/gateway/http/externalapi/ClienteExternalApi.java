package com.postech.gerencie.pedidos.gateway.http.externalapi;

import com.postech.gerencie.pedidos.usecase.dto.ClienteExternalDTO;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClienteExternalApi {

    private static final Logger log = LoggerFactory.getLogger(ClienteExternalApi.class);

    @Value("${pedidos.cliente-service.baseurl}")
    private String clienteServiceBaseUrl;

    private HttpFacade httpFacade;

    @PostConstruct
    void setUp() {
        httpFacade =  new HttpFacade(clienteServiceBaseUrl);
    }

    public ClienteExternalDTO buscarPorCpf (String cpf) {
        log.debug("Buscou o cliente por cpf: {}", cpf);
        ClienteExternalDTO clienteExternalDTO = httpFacade.get("/clientes/" + cpf, ClienteExternalDTO.class);
        return clienteExternalDTO;
    }

}
