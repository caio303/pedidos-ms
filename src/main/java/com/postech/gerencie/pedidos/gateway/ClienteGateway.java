package com.postech.gerencie.pedidos.gateway;

import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;

public interface ClienteGateway {

    SituacaoClienteDTO buscarSituacaoCliente(String cpf);
    Long buscarId(String cpf);
}
