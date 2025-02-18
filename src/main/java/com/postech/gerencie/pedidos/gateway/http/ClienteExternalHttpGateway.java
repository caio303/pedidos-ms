package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.gateway.http.externalapi.ClienteExternalApi;
import com.postech.gerencie.pedidos.usecase.dto.ClienteExternalDTO;
import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteExternalHttpGateway implements ClienteGateway {

    private final ClienteExternalApi clienteExternalApi;

    public ClienteExternalHttpGateway(ClienteExternalApi clienteExternalApi) {
        this.clienteExternalApi = clienteExternalApi;
    }

    @Override
    public SituacaoClienteDTO buscarSituacaoCliente(String cpf) {
        boolean cadastrado = false;
        boolean ativo = false;

        ClienteExternalDTO clienteExternalDTO = clienteExternalApi.buscarPorCpf(cpf);
        if (clienteExternalDTO != null) {
            cadastrado = true;
            ativo = clienteExternalDTO.ativo();
        }

        return new SituacaoClienteDTO(cadastrado, ativo);
    }
}
