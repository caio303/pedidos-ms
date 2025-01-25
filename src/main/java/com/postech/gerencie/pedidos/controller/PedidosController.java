package com.postech.gerencie.pedidos.controller;

import com.postech.gerencie.pedidos.usecase.dto.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @GetMapping("/cliente/{cpf-cliente}")
    public ResponseEntity<Collection<PedidoDTO>> listarPedidosPorCliente(@PathVariable(name = "cpf-cliente") String cpfCliente) {
        return ResponseEntity.ok(null);
    }

}
