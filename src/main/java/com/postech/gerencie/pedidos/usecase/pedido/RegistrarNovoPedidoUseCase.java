package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.exception.ClienteInativoException;
import com.postech.gerencie.pedidos.exception.ClienteNaoCadastradoException;
import com.postech.gerencie.pedidos.gateway.CatalogoGateway;
import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;
import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrarNovoPedidoUseCase {

    private static final Logger log = LoggerFactory.getLogger(RegistrarNovoPedidoUseCase.class);
    private final PedidoGateway pedidoGateway;
    private final ClienteGateway clienteGateway;
    private final CupomGateway cupomGateway;
    private final CatalogoGateway catalogoGateway;

    public RegistrarNovoPedidoUseCase(PedidoGateway pedidoGateway, ClienteGateway clienteGateway, CupomGateway cupomGateway, CatalogoGateway catalogoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.clienteGateway = clienteGateway;
        this.cupomGateway = cupomGateway;
        this.catalogoGateway = catalogoGateway;
    }

    public PedidoDTO registrarNovoPedido(NovoPedidoDTO novoPedidoDTO) {
        var cpfCliente = novoPedidoDTO.cpfCliente();

        SituacaoClienteDTO situacaoClienteDTO = clienteGateway.buscarSituacaoCliente(cpfCliente);
        validarSituacaoCliente(situacaoClienteDTO, cpfCliente);

        var mapItemQuantidade = novoPedidoDTO.getItemQuantidadeMap();
        var itemComValor = catalogoGateway.listarPorIds(mapItemQuantidade.keySet());

        var valorTotalItens = 0d;
        for (var item : itemComValor) {
            var quantidade = mapItemQuantidade.get(item.id());
            if (quantidade != null && quantidade > 0) {
                valorTotalItens += item.valor() * quantidade;
            }
        }

        var valorTotalPedido = valorTotalItens;

        var chaveCupomAplicado = novoPedidoDTO.cupomAplicado();
        if (chaveCupomAplicado != null && !chaveCupomAplicado.trim().isBlank()) {
            var cupom = cupomGateway.buscarPorChave(chaveCupomAplicado); // new Cupom("SECRETO", 0.1);

            if (cupom != null) {
                valorTotalPedido = cupom.descontar(valorTotalItens);
            }
        }

        var pedidoDTO = new PedidoDTO(
                novoPedidoDTO.cpfCliente(),
                StatusPedido.EM_ABERTO,
                StatusPedido.EM_ABERTO.getDescricaoPadrao(),
                novoPedidoDTO.quantidadeItemDTOS(),
                novoPedidoDTO.cupomAplicado(),
                valorTotalPedido,
                novoPedidoDTO.cepEntrega(),
                LocalDateTime.now(),
                null
        );

        pedidoGateway.registrarAberturaPedido(pedidoDTO);



        return pedidoDTO;
    }

    private void validarSituacaoCliente(SituacaoClienteDTO situacaoClienteDTO, String cpf) {
        if (!situacaoClienteDTO.cadastrado()) {
            log.warn("Cliente não cadastrado: {}", cpf);
            throw new ClienteNaoCadastradoException(cpf);
        } else if (!situacaoClienteDTO.ativo()) {
            log.warn("Cliente Inativo: {}", cpf);
            throw new ClienteInativoException(cpf);
        }
    }
}
