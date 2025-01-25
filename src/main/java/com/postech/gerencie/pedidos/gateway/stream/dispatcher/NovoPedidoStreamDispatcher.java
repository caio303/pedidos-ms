package com.postech.gerencie.pedidos.gateway.stream.dispatcher;

import com.postech.gerencie.pedidos.config.PedidoSeviceProperties;
import com.postech.gerencie.pedidos.domain.mapper.Mapper;
import com.postech.gerencie.pedidos.gateway.stream.messages.NovoPedidoMensagem;
import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class NovoPedidoStreamDispatcher implements NovoPedidoDispatcher {
    private static final Logger log = LoggerFactory.getLogger(NovoPedidoStreamDispatcher.class);

    private final StreamBridge streamBridge;
    private final PedidoSeviceProperties pedidoSeviceProperties;
    private final Mapper mapper;

    public NovoPedidoStreamDispatcher(StreamBridge streamBridge, PedidoSeviceProperties pedidoSeviceProperties, Mapper mapper) {
        this.streamBridge = streamBridge;
        this.pedidoSeviceProperties = pedidoSeviceProperties;
        this.mapper = mapper;
    }

    @Override
    public void enviar(NovoPedidoDTO novoPedidoDTO) {
        log.info("Novo Pedido enviado:  ");

        var novoPedidoMensagem = mapper.map(novoPedidoDTO, NovoPedidoMensagem.class);
        streamBridge.send(pedidoSeviceProperties.getNovoPedidoChannelName(), novoPedidoMensagem);
    }

}
