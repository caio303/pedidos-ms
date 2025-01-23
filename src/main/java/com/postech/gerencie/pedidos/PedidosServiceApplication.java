package com.postech.gerencie.pedidos;

import com.postech.gerencie.pedidos.config.DocumentationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosServiceApplication implements DocumentationConfig {

    public static void main(String[] args) {
        SpringApplication.run(PedidosServiceApplication.class, args);
    }

}
