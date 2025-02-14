package com.postech.gerencie.pedidos.controller.listeners;

import com.postech.gerencie.pedidos.gateway.queue.messages.AtualizacaoPedidoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AtualizacaoPedidoStreamListener implements AtualizacaoPedidoListener {
    private static final Logger log = LoggerFactory.getLogger(AtualizacaoPedidoStreamListener.class);

    @Override
    public Consumer<Message<AtualizacaoPedidoMensagem>> processarAtualizacaoPedido() {
        return mensagem -> {
            log.info("Consumiu Atualização Pedido: {}", mensagem.getPayload());
        };
    }
}