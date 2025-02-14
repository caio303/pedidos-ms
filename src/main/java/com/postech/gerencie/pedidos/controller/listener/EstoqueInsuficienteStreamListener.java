package com.postech.gerencie.pedidos.controller.listener;

import com.postech.gerencie.pedidos.gateway.queue.messages.EstoqueInsuficienteMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EstoqueInsuficienteStreamListener implements Consumer<Message<EstoqueInsuficienteMensagem>> {

    private static final Logger log = LoggerFactory.getLogger(EstoqueInsuficienteStreamListener.class);

    @Override
    public void accept(Message<EstoqueInsuficienteMensagem> estoqueInsuficienteMensagemMessage) {
        log.info("Consumiu Estoque Insuficiente: {}", estoqueInsuficienteMensagemMessage);
    }
}
