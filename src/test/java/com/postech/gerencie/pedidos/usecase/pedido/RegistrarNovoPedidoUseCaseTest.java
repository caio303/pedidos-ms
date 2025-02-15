package com.postech.gerencie.pedidos.usecase.pedido;

import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.domain.enums.StatusPedido;
import com.postech.gerencie.pedidos.exception.ClienteInativoException;
import com.postech.gerencie.pedidos.exception.ClienteInexistenteException;
import com.postech.gerencie.pedidos.exception.FormatoInvalidoException;
import com.postech.gerencie.pedidos.gateway.CatalogoGateway;
import com.postech.gerencie.pedidos.gateway.ClienteGateway;
import com.postech.gerencie.pedidos.gateway.CupomGateway;
import com.postech.gerencie.pedidos.gateway.PedidoGateway;
import com.postech.gerencie.pedidos.usecase.dto.ItemComIdDTO;
import com.postech.gerencie.pedidos.usecase.dto.QuantidadeItemDTO;
import com.postech.gerencie.pedidos.usecase.dto.SituacaoClienteDTO;
import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegistrarNovoPedidoUseCaseTest {

    private RegistrarNovoPedidoUseCase subject;

    @Spy private PedidoGateway pedidoGateway;
    @Spy private ClienteGateway clienteGateway;
    @Spy private CupomGateway cupomGateway;
    @Spy private CatalogoGateway catalogoGateway;

    private final String cpfCliente = "12345678901";
    private final String cepEntrega = "12345678";

    private final String chaveCupom = "SECRETO";
    private final Cupom cupomComLimite = new Cupom(chaveCupom, 0.1d, 15d);

    private final Collection<QuantidadeItemDTO> itens = List.of(
            new QuantidadeItemDTO(1L, 2d),
            new QuantidadeItemDTO(2L, 2d)
    );
    private final Collection<ItemComIdDTO> itensDTO = List.of(
            new ItemComIdDTO(1L, "Fone", 75d),
            new ItemComIdDTO(2L, "Mouse", 25d)
    );

    @BeforeEach
    void setUp() {
        this.pedidoGateway = mock(PedidoGateway.class);
        this.clienteGateway = mock(ClienteGateway.class);
        this.cupomGateway = mock(CupomGateway.class);
        this.catalogoGateway = mock(CatalogoGateway.class);
        this.subject = new RegistrarNovoPedidoUseCase(pedidoGateway, clienteGateway, cupomGateway, catalogoGateway);
    }

    @Test
    void registrarNovoPedidoComSucesso() {
        var novoPedidoDTO = new NovoPedidoDTO(
                cpfCliente,
                itens,
                chaveCupom,
                cepEntrega
        );

        var situacaoClienteDTO = new SituacaoClienteDTO(true, true);

        when(clienteGateway.buscarSituacaoCliente(cpfCliente)).thenReturn(situacaoClienteDTO);
        when(catalogoGateway.listarPorIds(any())).thenReturn(itensDTO);
        when(cupomGateway.buscarPorChave(chaveCupom)).thenReturn(cupomComLimite);

        var itensQuantidadesMap = novoPedidoDTO.getItemQuantidadeMap();

        var valorTotalItens = getValorTotalItens(itensQuantidadesMap, itensDTO);

        var limiteDesconto = cupomComLimite.getLimiteDesconto();
        var expectedValorTotal = valorTotalItens - limiteDesconto;

        var pedidoDTO = subject.registrarNovoPedido(novoPedidoDTO);

        assertEquals(cpfCliente, pedidoDTO.cpfCliente());
        assertEquals(cepEntrega, pedidoDTO.cepEntrega());
        assertEquals(cupomComLimite.getChave(), pedidoDTO.cupomAplicado());
        assertEquals(StatusPedido.INICIADO.getId(), pedidoDTO.status().getId());
        assertEquals(expectedValorTotal, pedidoDTO.valorTotal());
        assertEquals(itensQuantidadesMap.size(), pedidoDTO.itens().size());

        assertNotNull(pedidoDTO.dataCriacao());
        assertNull(pedidoDTO.dataAtualizacao());
        assertNull(pedidoDTO.codigoRastreio());
    }

    @Test
    void barrarClienteNaoCadastrado() {
        var novoPedidoDTO = new NovoPedidoDTO(
                cpfCliente,
                itens,
                chaveCupom,
                cepEntrega
        );

        var situacaoClienteDTO = new SituacaoClienteDTO(false, false);

        when(clienteGateway.buscarSituacaoCliente(cpfCliente)).thenReturn(situacaoClienteDTO);

        assertThrows(ClienteInexistenteException.class, () -> subject.registrarNovoPedido(novoPedidoDTO));
    }

    @Test
    void barrarClienteInativo() {
        var novoPedidoDTO = new NovoPedidoDTO(
                cpfCliente,
                itens,
                chaveCupom,
                cepEntrega
        );

        var situacaoClienteDTO = new SituacaoClienteDTO(true, false);

        when(clienteGateway.buscarSituacaoCliente(cpfCliente)).thenReturn(situacaoClienteDTO);

        assertThrows(ClienteInativoException.class, () -> subject.registrarNovoPedido(novoPedidoDTO));
    }

    @Test
    void deveJogarExcecaoComCPFInvalido() {
        var novoPedidoDTO = new NovoPedidoDTO(
                "cpfinvalido",
                itens,
                chaveCupom,
                cepEntrega
        );

        assertThrows(FormatoInvalidoException.class, () -> subject.registrarNovoPedido(novoPedidoDTO));
    }

    @Test
    void deveJogarExcecaoComCEPInvalido() {
        var novoPedidoDTO = new NovoPedidoDTO(
                cpfCliente,
                itens,
                chaveCupom,
                "cepinvalido"
        );

        assertThrows(FormatoInvalidoException.class, () -> subject.registrarNovoPedido(novoPedidoDTO));
    }

    private Double getValorTotalItens(Map<Long, Double> itensQuantidadesMap, Collection<ItemComIdDTO> itensDTO) {
        var result = 0d;
        for (var item : itensDTO) {
            result += itensQuantidadesMap.get(item.id()) * item.valor();
        }

        return result;
    }

}