package com.postech.gerencie.pedidos.controller;

import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import com.postech.gerencie.pedidos.usecase.dto.novopedido.NovoPedidoDTO;
import com.postech.gerencie.pedidos.usecase.pedido.RegistrarNovoPedidoUseCase;
import jakarta.validation.Valid;
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

    public PedidosController(RegistrarNovoPedidoUseCase registrarNovoPedidoUseCase) {
        this.registrarNovoPedidoUseCase = registrarNovoPedidoUseCase;
    }

    @GetMapping("/cliente/{cpf-cliente}")
    public ResponseEntity<Collection<PedidoDTO>> listarPedidosPorCliente(@PathVariable(name = "cpf-cliente") String cpfCliente) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/novo-pedido")
    public ResponseEntity<PedidoDTO> novoPedido(@RequestBody @Valid NovoPedidoDTO novoPedidoDTO) {
        PedidoDTO pedidoDTO = registrarNovoPedidoUseCase.registrarNovoPedido(novoPedidoDTO);
        return ResponseEntity.ok(pedidoDTO);
    }

}
