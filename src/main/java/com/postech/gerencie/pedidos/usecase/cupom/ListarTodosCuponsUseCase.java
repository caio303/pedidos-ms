package com.postech.gerencie.pedidos.usecase.cupom;

import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ListarTodosCuponsUseCase {

    private final CupomGateway cupomGateway;

    public ListarTodosCuponsUseCase(CupomGateway cupomGateway) {
        this.cupomGateway = cupomGateway;
    }

    public Collection<Cupom> listarTodos() {
        return cupomGateway.listarTodos();
    }
}
