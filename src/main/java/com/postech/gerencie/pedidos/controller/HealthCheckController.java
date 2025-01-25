package com.postech.gerencie.pedidos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @Value("${application.name}")
    private String applicationName;

    @GetMapping
    public ResponseEntity<String> verificarServicoAtivo() {
        return ResponseEntity.ok(getOkMessage());
    }

    private String getOkMessage() {
        return applicationName + " - OK";
    }
}
