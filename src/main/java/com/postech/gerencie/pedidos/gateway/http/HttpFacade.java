package com.postech.gerencie.pedidos.gateway.http;

import io.netty.channel.ChannelOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class HttpFacade {

    private static final Logger log = LoggerFactory.getLogger(HttpFacade.class);

    private static final long TIMEOUT_LIMIT = 1000;

    private final WebClient webClient;
    private final String baseUrl;

    public HttpFacade(String baseUrl) {
        var httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000));

        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public <T> T get(String uri, Map<String, Object> parameters, Class<T> returnClazz) {
        T responseEntity = null;

        try {
            var response = webClient.get()
                    .uri(uri)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve();
            responseEntity = response.bodyToMono(returnClazz)
                    .block(Duration.of(TIMEOUT_LIMIT, ChronoUnit.MILLIS));
        } catch (WebClientException error) {
            log.error("Erro na requisição get: {}, {}, {}, {}", this.baseUrl, uri, parameters, returnClazz);
        }

        return responseEntity;
    }

    public <T> T get(String s, Class<T> returnClazz) {
        return this.get(s, null, returnClazz);
    }
}
