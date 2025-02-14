package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.gateway.database.jpa.repository.ClienteExternalRepository;
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

    private final ClienteExternalRepository clienteExternalRepository;

    public ClienteExternalHttpGateway(ClienteExternalRepository clienteExternalRepository) {
        this.clienteExternalRepository = clienteExternalRepository;
    }

//    @Override
//    public void cadastrarCliente(String cpf) {
//        if (clienteExternalRepository.existsByCpf(cpf)) {
//            return;
//        }
//
//        ClienteExternalDTO clienteExternalDTO = buscarClientePorCpf(cpf);
//        if (clienteExternalDTO == null) {
//            log.warn("Cliente não encontrado no serviço externo: {}", cpf);
//            return;
//        }
//
//        var clienteExternal = new ClienteExternal(clienteExternalDTO.id(), cpf);
//
//        clienteExternalRepository.save(clienteExternal);
//    }

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

    @Override
    public Long buscarId(String cpf) {
        var clienteDTO = buscarClientePorCpf(cpf);
        if (clienteDTO == null) {
            log.debug("Cliente não encontrado: {}", cpf);
            return null;
        }
        return clienteDTO.id();
    }

    private ClienteExternalDTO buscarClientePorCpf(String cpf) {
        log.debug("Buscou o cliente por cpf: {}", cpf);
        return clienteExternalApi.get("/clientes/" + cpf, ClienteExternalDTO.class);
    }
}
