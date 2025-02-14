package com.postech.gerencie.pedidos.controller.listener;

import com.postech.gerencie.pedidos.gateway.queue.messages.AtualizacaoPedidoMensagem;
import com.postech.gerencie.pedidos.usecase.pedido.AtualizarPedidoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AtualizacaoPedidoStreamListener implements Consumer<Message<AtualizacaoPedidoMensagem>> {
    private static final Logger log = LoggerFactory.getLogger(AtualizacaoPedidoStreamListener.class);

    private final AtualizarPedidoUseCase atualizarPedidoUseCase;

    public AtualizacaoPedidoStreamListener(AtualizarPedidoUseCase atualizarPedidoUseCase) {
        this.atualizarPedidoUseCase = atualizarPedidoUseCase;
    }

    @Override
    public void accept(Message<AtualizacaoPedidoMensagem> mensagem) {
        var payload = mensagem.getPayload();
        log.info("Atualizando status pedido: {}", payload);
        atualizarPedidoUseCase.atualizarPedido(payload.pedidoId(), payload.novoStatusId(), payload.codigoEntrega());
    }
}