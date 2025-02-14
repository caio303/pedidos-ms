package com.postech.gerencie.pedidos.controller.listeners;

import com.postech.gerencie.pedidos.gateway.queue.messages.AtualizacaoPedidoMensagem;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

public interface EstoqueInsuficienteListener {

    Consumer<Message<AtualizacaoPedidoMensagem>> processarEstoqueInsuficiente();
}
