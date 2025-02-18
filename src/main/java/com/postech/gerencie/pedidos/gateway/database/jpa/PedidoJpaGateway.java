package com.postech.gerencie.pedidos.gateway.database.jpa;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.exception.PedidoInexistenteException;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Pedido;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.PedidoItem;
import com.postech.gerencie.pedidos.gateway.database.jpa.mapper.PedidoMapper;
import com.postech.gerencie.pedidos.gateway.database.jpa.repository.PedidoItemRepository;
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

    private final PedidoMapper pedidoMapper = new PedidoMapper();

    private final PedidoRepository pedidoRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final CupomGateway cupomGateway;
    private final NovoPedidoDispatcher novoPedidoDispatcher;

    public PedidoJpaGateway(PedidoRepository pedidoRepository, PedidoItemRepository pedidoItemRepository, CupomGateway cupomGateway, NovoPedidoDispatcher novoPedidoDispatcher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.cupomGateway = cupomGateway;
        this.novoPedidoDispatcher = novoPedidoDispatcher;
    }

    @Override
    public void registrarAberturaPedido(PedidoDTO pedidoDTO) {
        var chaveCupom = pedidoDTO.cupomAplicado();
        Long cupomId = null;

        if (chaveCupom != null) {
            cupomId = cupomGateway.buscarIdPorChave(chaveCupom);
        }

        var pedido = pedidoMapper.toEntity(pedidoDTO, cupomId);
        var pedidoSalvo = pedidoRepository.save(pedido);

        var pedidoId = pedidoSalvo.getId();
        var itens = pedidoDTO.itens()
                .entrySet()
                .stream()
                .map(entry -> new PedidoItem(pedidoId, entry.getKey(), entry.getValue()))
                .toList();

        pedidoItemRepository.saveAll(itens);

        novoPedidoDispatcher.enviar(pedidoId, pedidoDTO);
    }

    @Override
    public void atualizarStatusPedido(long pedidoId, StatusPedido novoStatus, String codigoRastreio) {
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
        List<Pedido> allByCpfCliente = pedidoRepository.findAllByCpfCliente(cpf);

        return allByCpfCliente.stream().map(pedidoMapper::toDomain).toList();
    }


}
