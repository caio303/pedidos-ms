package com.postech.gerencie.pedidos.gateway.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpFacade {

    private static final Logger log = LoggerFactory.getLogger(HttpFacade.class);

    private final RestClient restClient;
    private final String baseUrl;

    public HttpFacade(String baseUrl) {
        this.baseUrl = baseUrl;

        this.restClient = RestClient.builder()
                .baseUrl(this.baseUrl)
                .build();
    }

    public <T> T get(String uri, Map<String, Object> parameters, Class<T> returnClazz) {
        T responseEntity = null;

        try {
            var response = restClient.get()
                    .uri(uri)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve();

            responseEntity = response.body(returnClazz);
        } catch (Exception error) {
            log.error("Erro na requisição get: {}, {}, {}, {}", this.baseUrl, uri, parameters, returnClazz);
        }

        return responseEntity;
    }

    public <T> T get(String s, Class<T> returnClazz) {
        return this.get(s, null, returnClazz);
    }
}
