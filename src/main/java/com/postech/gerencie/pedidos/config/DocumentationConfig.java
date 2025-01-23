package com.postech.gerencie.pedidos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Pedidos Service",
                version = "0.0.1-mvp",
                description = "Pedidos Service MVP - Tech Challenge Fase 4",
                contact = @Contact(
                        name = "Grupo 14 - 5ADJT",
                        email = "caioalves_diogo@hotmail.com"
                )
        )
)
public interface DocumentationConfig { }