package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.usecase.dto.ClienteExternalDTO;
import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClienteExternalHttpGateway implements ClienteGateway {

    private static final Logger log = LoggerFactory.getLogger(ClienteExternalHttpGateway.class);

    @Value("${pedidos.cliente-service.baseurl}")
    private String clienteServiceBaseUrl;

    private final HttpFacade clienteExternalApi =  new HttpFacade(clienteServiceBaseUrl);

    @Override
    public SituacaoClienteDTO buscarSituacaoCliente(String cpf) {
        boolean cadastrado = false
                ,ativo = false;

        ClienteExternalDTO clienteExternalDTO = buscarClientePorCpf(cpf);
        if (clienteExternalDTO != null) {
            cadastrado = true;
            ativo = clienteExternalDTO.ativo();
        }

        return new SituacaoClienteDTO(cadastrado, ativo);
    }

    private ClienteExternalDTO buscarClientePorCpf(String cpf) {
        log.debug("Buscou o cliente por cpf: {}", cpf);
        return clienteExternalApi.get("/clientes/" + cpf, ClienteExternalDTO.class);
    }
}
