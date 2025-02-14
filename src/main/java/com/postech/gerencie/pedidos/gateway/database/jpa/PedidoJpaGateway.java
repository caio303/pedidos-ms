package com.postech.gerencie.pedidos.gateway.database.jpa;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.exception.PedidoInexistenteException;
import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Pedido;
import com.postech.gerencie.pedidos.gateway.database.jpa.repository.PedidoRepository;
import com.postech.gerencie.pedidos.gateway.queue.dispatcher.NovoPedidoDispatcher;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoJpaGateway implements PedidoGateway {

    private static final Logger log = LoggerFactory.getLogger(PedidoJpaGateway.class);
    private final PedidoRepository pedidoRepository;
    private final CupomGateway cupomGateway;
    private final NovoPedidoDispatcher novoPedidoDispatcher;
    private final ClienteGateway clienteGateway;

    public PedidoJpaGateway(PedidoRepository pedidoRepository, CupomGateway cupomGateway, NovoPedidoDispatcher novoPedidoDispatcher, ClienteGateway clienteGateway) {
        this.pedidoRepository = pedidoRepository;
        this.cupomGateway = cupomGateway;
        this.novoPedidoDispatcher = novoPedidoDispatcher;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void registrarAberturaPedido(PedidoDTO pedidoDTO) {
        var chaveCupom = pedidoDTO.cupomAplicado();
        Long cupomId = null;

        if (chaveCupom != null) {
            cupomId = cupomGateway.buscarIdPorChave(chaveCupom);
        }

        var pedido = toEntity(pedidoDTO, cupomId);
        pedidoRepository.save(pedido);
        novoPedidoDispatcher.enviar(pedidoDTO);
    }

    @Override
    public void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus, String codigoRastreio) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);

        if (pedidoOpt.isEmpty()) {
            log.warn("Pedido Inexistente: {}", pedidoId);
            throw new PedidoInexistenteException(pedidoId);
        }

        var pedido = pedidoOpt.get();
        pedido.setStatusId(novoStatus.getId());
        pedido.setDataAtualizacao(LocalDateTime.now());

        if (codigoRastreio != null && !codigoRastreio.trim().isBlank() && !codigoRastreio.equals(pedido.getCodigoRastreio())) {
            pedido.setCodigoRastreio(codigoRastreio);
        }

        log.debug("Pedido atualizado: id={}, novoStatus={}, codigoRastreio={}", pedidoId, novoStatus, codigoRastreio);
    }

    @Override
    public List<com.postech.gerencie.pedidos.domain.Pedido> listarPorCpf(String cpf) {
        pedidoRepository.findAllByCpfCliente(cpf);
        return List.of();
    }

    private Pedido toEntity(PedidoDTO pedidoDTO, Long idCupom) {
        var pedido = new Pedido();

        pedido.setCpfCliente(pedidoDTO.cpfCliente());
        pedido.setCupomId(idCupom);

        pedido.setCepEntrega(pedidoDTO.cepEntrega());
        pedido.setCodigoRastreio(pedidoDTO.codigoRastreio());
        pedido.setStatusId(pedidoDTO.status().getId());

        pedido.setDataCriacao(pedidoDTO.dataCriacao());
        pedido.setDataAtualizacao(pedidoDTO.dataAtualizacao());

        return pedido;
    }

}
