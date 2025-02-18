package com.postech.gerencie.pedidos.gateway.queue.dispatcher;

import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;

public interface NovoPedidoDispatcher {

    void enviar(Long pedidoId, PedidoDTO pedidoDTO);
}
