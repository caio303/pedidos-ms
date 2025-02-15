package com.postech.gerencie.pedidos.controller;

import com.postech.gerencie.pedidos.controller.json.NovoCupomJson;
import com.postech.gerencie.pedidos.domain.Cupom;
import com.postech.gerencie.pedidos.usecase.cupom.CriarCupomUseCase;
import com.postech.gerencie.pedidos.usecase.cupom.ExcluirCupomUseCase;
import com.postech.gerencie.pedidos.usecase.cupom.ListarTodosCuponsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    private final CriarCupomUseCase criarCupomUseCase;
    private final ExcluirCupomUseCase excluirCupomUseCase;
    private final ListarTodosCuponsUseCase listarTodosCupons;

    public CupomController(CriarCupomUseCase criarCupomUseCase, ExcluirCupomUseCase excluirCupomUseCase, ListarTodosCuponsUseCase listarTodosCupons) {
        this.criarCupomUseCase = criarCupomUseCase;
        this.excluirCupomUseCase = excluirCupomUseCase;
        this.listarTodosCupons = listarTodosCupons;
    }

    @GetMapping
    public ResponseEntity<Collection<Cupom>> listarTodos() {
        var cupons = listarTodosCupons.listarTodos();
        return ResponseEntity.ok(cupons);
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
