package com.postech.gerencie.pedidos.gateway.queue.dispatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.gerencie.pedidos.config.PedidoSeviceProperties;
import com.postech.gerencie.pedidos.domain.mapper.Mapper;
import com.postech.gerencie.pedidos.gateway.queue.messages.NovoPedidoMensagem;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
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
    private final ObjectMapper objectMapper;

    public NovoPedidoStreamDispatcher(StreamBridge streamBridge, PedidoSeviceProperties pedidoSeviceProperties, Mapper mapper, ObjectMapper objectMapper) {
        this.streamBridge = streamBridge;
        this.pedidoSeviceProperties = pedidoSeviceProperties;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enviar(PedidoDTO pedidoDTO) {
        log.info("Novo Pedido enviado para '{}':  ", pedidoSeviceProperties.getNovoPedidoChannelName());

        var mensagem = new NovoPedidoMensagem(pedidoDTO.cpfCliente(), pedidoDTO.itens(), pedidoDTO.cepEntrega());

        streamBridge.send(pedidoSeviceProperties.getNovoPedidoChannelName(), mensagem);
    }

}
