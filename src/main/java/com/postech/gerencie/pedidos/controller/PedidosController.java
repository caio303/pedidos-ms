package com.postech.gerencie.pedidos.controller;

import com.postech.gerencie.pedidos.controller.json.NovoPedidoJson;
import com.postech.gerencie.pedidos.domain.Pedido;
import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import com.postech.gerencie.pedidos.usecase.pedido.ListarPedidosPorClienteUseCase;
import com.postech.gerencie.pedidos.usecase.pedido.RegistrarNovoPedidoUseCase;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final RegistrarNovoPedidoUseCase registrarNovoPedidoUseCase;
    private final ListarPedidosPorClienteUseCase listarPedidosPorClienteUseCase;

    public PedidosController(RegistrarNovoPedidoUseCase registrarNovoPedidoUseCase, ListarPedidosPorClienteUseCase listarPedidosPorClienteUseCase) {
        this.registrarNovoPedidoUseCase = registrarNovoPedidoUseCase;
        this.listarPedidosPorClienteUseCase = listarPedidosPorClienteUseCase;
    }

    @GetMapping("/{cpf-cliente}")
    public ResponseEntity<Collection<Pedido>> listarPedidosPorCliente(@PathVariable(name = "cpf-cliente") @CPF @Valid String cpfCliente) {
        if (cpfCliente == null || cpfCliente.isBlank()) {
            return ResponseEntity.notFound().build();
        }

        cpfCliente = cpfCliente.replaceAll("\\D", "");

        var pedidos = listarPedidosPorClienteUseCase.listarPorCpf(cpfCliente);
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/novo-pedido")
    public ResponseEntity<PedidoDTO> novoPedido(@RequestBody @Valid NovoPedidoJson novoPedidoJson) {
        PedidoDTO pedidoDTO = registrarNovoPedidoUseCase.registrarNovoPedido(novoPedidoJson.toDTO());
        return ResponseEntity.ok(pedidoDTO);
    }

}
