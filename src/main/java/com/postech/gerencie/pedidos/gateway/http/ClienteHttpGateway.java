package com.postech.gerencie.pedidos.gateway.http;

import com.postech.gerencie.pedidos.gateway.ClienteGateway;

public class ClienteHttpGateway implements ClienteGateway {

    // criar projetinho mockado pra testar a comunicacao
    @Override
    public boolean validarClienteAtivo(String cpfCliente) {
        return true;
    }
}
