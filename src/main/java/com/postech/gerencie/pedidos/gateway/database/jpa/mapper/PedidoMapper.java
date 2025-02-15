package com.postech.gerencie.pedidos.gateway.database.jpa.mapper;

import com.postech.gerencie.pedidos.domain.QuantidadeItem;
import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Cupom;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.Pedido;
import com.postech.gerencie.pedidos.gateway.database.jpa.entities.PedidoItem;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PedidoMapper {

    private static final Logger log = LoggerFactory.getLogger(PedidoMapper.class);

    public com.postech.gerencie.pedidos.domain.Pedido toDomain(Pedido entity) {
        var status = StatusPedido.deId(entity.getStatusId());
        if (status == null) {
            log.error("Falhou em mapear entity/Pedido para domain/Pedido");
            throw new RuntimeException("Falhou em mapear entity/Pedido para domain/Pedido");
        }

        String chaveCupom = null;
        if (entity.getCupom() != null) {
            chaveCupom = entity.getCupom().getChave();
        }

        var quantidadeItens = entity.getItensPedido()
                .stream()
                .map(this::toQuantidadeItem)
                .toList();

        return new com.postech.gerencie.pedidos.domain.Pedido(
                entity.getCpfCliente(),
                status,
                status.getDescricaoPadrao(),
                quantidadeItens,
                chaveCupom,
                entity.getValorTotal(),
                entity.getCepEntrega(),
                entity.getCodigoRastreio(),
                entity.getDataCriacao(),
                entity.getDataAtualizacao()
        );
    }

    private QuantidadeItem toQuantidadeItem(PedidoItem pedidoItem) {
        return new QuantidadeItem(pedidoItem.getItemId(), pedidoItem.getQuantidade());
    }

    public Pedido toEntity(PedidoDTO pedidoDTO, Long idCupom) {
        var pedido = new Pedido();

        pedido.setCpfCliente(pedidoDTO.cpfCliente());
        pedido.setCupom(new Cupom(idCupom, null, null, null));

        pedido.setCepEntrega(pedidoDTO.cepEntrega());
        pedido.setCodigoRastreio(pedidoDTO.codigoRastreio());
        pedido.setStatusId(pedidoDTO.status().getId());

        pedido.setDataCriacao(pedidoDTO.dataCriacao());
        pedido.setDataAtualizacao(pedidoDTO.dataAtualizacao());

        // não popula os itens
        return pedido;
    }
}
