package com.postech.gerencie.pedidos.controller.listeners;

import com.postech.gerencie.pedidos.gateway.queue.messages.AtualizacaoPedidoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EstoqueInsuficienteStreamListenerImpl implements EstoqueInsuficienteListener {

    private static final Logger log = LoggerFactory.getLogger(EstoqueInsuficienteStreamListenerImpl.class);

    @Override
    public Consumer<Message<AtualizacaoPedidoMensagem>> processarEstoqueInsuficiente() {
        return mensagem -> {
            log.info("Consumiu Estoque Insuficiente: {}", mensagem.getPayload());
        };
    }
}
