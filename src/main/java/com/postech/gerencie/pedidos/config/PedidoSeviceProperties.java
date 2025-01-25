package com.postech.gerencie.pedidos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoSeviceProperties {

    @Value("${queue.topics.novo-pedido.name}")
    private String novoPedidoChannelName;

    @Value("${queue.topics.estoque-insuficiente.name}")
    private String estoqueInsuficienteChannelName;

    @Value("${queue.topics.entrega-iniciada-topic.name}")
    private String entregaIniciadaChannelName;

    @Value("${queue.topics.entrega-concluida.name}")
    private String entregaConcluidaChannelName;


    public String getNovoPedidoChannelName() {
        return novoPedidoChannelName;
    }

    public String getEstoqueInsuficienteChannelName() {
        return estoqueInsuficienteChannelName;
    }

    public String getEntregaIniciadaChannelName() {
        return entregaIniciadaChannelName;
    }

    public String getEntregaConcluidaChannelName() {
        return entregaConcluidaChannelName;
    }
}
