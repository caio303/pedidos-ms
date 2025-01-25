package com.postech.gerencie.pedidos.gateway.stream.dispatcher;

import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;

public interface NovoPedidoDispatcher {

    void enviar(NovoPedidoDTO novoPedidoDTO);

}
