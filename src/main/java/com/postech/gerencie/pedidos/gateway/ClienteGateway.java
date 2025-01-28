package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;

public interface ClienteGateway {

    void cadastrarCliente(String cpf);
    SituacaoClienteDTO buscarSituacaoCliente(String cpf);

}
