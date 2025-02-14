package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.domain.validator.ValidadorCep;
import com.postech.gerencie.pedidos.domain.validator.ValidadorCpf;
import com.postech.gerencie.pedidos.exception.ClienteInativoException;
import com.postech.gerencie.pedidos.exception.ClienteInexistenteException;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.CatalogoGateway;
import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;
import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;
import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class RegistrarNovoPedidoUseCase {

    private static final Logger log = LoggerFactory.getLogger(RegistrarNovoPedidoUseCase.class);
    private final PedidoGateway pedidoGateway;
    private final ClienteGateway clienteGateway;
    private final CupomGateway cupomGateway;
    private final CatalogoGateway catalogoGateway;

    public RegistrarNovoPedidoUseCase(PedidoGateway pedidoGateway, ClienteGateway clienteGateway, CupomGateway cupomGateway, CatalogoGateway catalogoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.clienteGateway = clienteGateway;
        this.cupomGateway = cupomGateway;
        this.catalogoGateway = catalogoGateway;
    }

    public PedidoDTO registrarNovoPedido(NovoPedidoDTO novoPedidoDTO) {
        var cpfCliente = novoPedidoDTO.cpfCliente();
        var cepEntrega = novoPedidoDTO.cepEntrega();

        validarCpf(cpfCliente);
        validarCep(cepEntrega);

        SituacaoClienteDTO situacaoClienteDTO = clienteGateway.buscarSituacaoCliente(cpfCliente);
        validarSituacaoCliente(situacaoClienteDTO, cpfCliente);

        var mapItemQuantidade = novoPedidoDTO.getItemQuantidadeMap();
        var itensComValor = catalogoGateway.listarPorIds(mapItemQuantidade.keySet());

        var valorTotalItens = 0d;
        for (var item : itensComValor) {
            var quantidade = mapItemQuantidade.get(item.id());
            if (quantidade != null && quantidade > 0) {
                valorTotalItens += item.valor() * quantidade;
            }
        }

        var valorTotalPedido = valorTotalItens;

        var chaveCupomAplicado = novoPedidoDTO.cupomAplicado();
        if (chaveCupomAplicado != null && !chaveCupomAplicado.trim().isBlank()) {
            var cupom = cupomGateway.buscarPorChave(chaveCupomAplicado);

            if (cupom != null) {
                valorTotalPedido = cupom.descontar(valorTotalItens);
            }
        }

        var pedidoDTO = new PedidoDTO(
                novoPedidoDTO.cpfCliente(),
                StatusPedido.INICIADO,
                novoPedidoDTO.quantidadeItemDTOS().stream().collect(Collectors.toMap(
                        QuantidadeItemDTO::itemId,
                        QuantidadeItemDTO::quantidade
                )),
                novoPedidoDTO.cupomAplicado(),
                valorTotalPedido,
                cepEntrega,
                null,
                LocalDateTime.now(),
                null
        );

        pedidoGateway.registrarAberturaPedido(pedidoDTO);

        return pedidoDTO;
    }

    private void validarCep(String cepEntrega) {
        var validador = new ValidadorCep();
        if (!validador.ehCEPValido(cepEntrega)) {
            log.debug("CEP invalido recebido: {}", cepEntrega);
            throw new FormatoInvalidoException("cepEntrega", cepEntrega);
        }
    }

    private void validarCpf(String cpfCliente) {
        var validador = new ValidadorCpf();
        if (!validador.ehCPFValido(cpfCliente)) {
            log.debug("CPF invalido recebido: {}", cpfCliente);
            throw new FormatoInvalidoException("cpfCliente", cpfCliente);
        }
    }

    private void validarSituacaoCliente(SituacaoClienteDTO situacaoClienteDTO, String cpf) {
        if (situacaoClienteDTO == null || !situacaoClienteDTO.cadastrado()) {
            log.warn("Cliente não cadastrado: {}", cpf);
            throw new ClienteInexistenteException(cpf);
        } else if (!situacaoClienteDTO.ativo()) {
            log.warn("Cliente Inativo: {}", cpf);
            throw new ClienteInativoException(cpf);
        }
    }
}
