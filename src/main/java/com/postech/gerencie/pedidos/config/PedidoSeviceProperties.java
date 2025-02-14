package com.postech.gerencie.pedidos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoSeviceProperties {

    @Value("${queue.topics.novo-pedido.name}")
    private String novoPedidoChannelName;

    public String getNovoPedidoChannelName() {
        return novoPedidoChannelName;
    }

}
