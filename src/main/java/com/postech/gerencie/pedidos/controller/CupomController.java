package com.postech.gerencie.pedidos.controller;

import com.postech.gerencie.pedidos.controller.json.NovoCupomJson;
import com.postech.gerencie.pedidos.usecase.cupom.CriarCupomUseCase;
import com.postech.gerencie.pedidos.usecase.cupom.ExcluirCupomUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    private final CriarCupomUseCase criarCupomUseCase;
    private final ExcluirCupomUseCase excluirCupomUseCase;

    public CupomController(CriarCupomUseCase criarCupomUseCase, ExcluirCupomUseCase excluirCupomUseCase) {
        this.criarCupomUseCase = criarCupomUseCase;
        this.excluirCupomUseCase = excluirCupomUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> criarCupom(@RequestBody @Valid NovoCupomJson novoCupomJson) {
        criarCupomUseCase.criarCupom(novoCupomJson.toDTO());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{chave}")
    public ResponseEntity<Void> excluirCupom(@PathVariable String chave) {
        excluirCupomUseCase.excluirCupom(chave);
        return ResponseEntity.noContent().build();
    }
}
