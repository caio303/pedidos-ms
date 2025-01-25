package com.postech.gerencie.pedidos.usecase;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.gateway.CatalogoGateway;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrarNovoPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final CupomGateway cupomGateway;
    private final CatalogoGateway catalogoGateway;

    public RegistrarNovoPedidoUseCase(PedidoGateway pedidoGateway, CupomGateway cupomGateway, CatalogoGateway catalogoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.cupomGateway = cupomGateway;
        this.catalogoGateway = catalogoGateway;
    }

    public void registrarNovoPedido(NovoPedidoDTO novoPedidoDTO) {
        //validar cliente ativo

        var valorTotalItens = 0d;
        // buscar valor de cada item
        var itensComValor = catalogoGateway.listarPorIds(List.of());
        valorTotalItens += 1;

        var valorTotalPedido = valorTotalItens;

        var chaveCupomAplicado = novoPedidoDTO.cupomAplicado();
        if (chaveCupomAplicado != null && !chaveCupomAplicado.trim().isBlank()) {
            var cupom = cupomGateway.buscarPorChave(chaveCupomAplicado); // new Cupom("SECRETO", 0.1);

            valorTotalPedido = cupom.descontar(valorTotalItens);
        }

        var pedidoDTO = new PedidoDTO(
                novoPedidoDTO.cpfCliente(),
                StatusPedido.EM_ABERTO,
                StatusPedido.EM_ABERTO.getDescricaoPadrao(),
                novoPedidoDTO.itemIds(),
                novoPedidoDTO.cupomAplicado(),
                valorTotalPedido,
                novoPedidoDTO.cepEntrega(),
                LocalDateTime.now(),
                null
        );

    }
}
