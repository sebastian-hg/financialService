package com.mycompany.financialservices.client.impl;

import com.mycompany.financialservices.client.CryptoClient;
import com.mycompany.financialservices.configuration.ClientConfigProperties;
import com.mycompany.financialservices.exception.ErrorInCallToApiException;
import com.mycompany.financialservices.model.dto.response.CryptoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class BuenBitCryptoClient implements CryptoClient {
    private final ClientConfigProperties properties;

    @Override
    public Mono<CryptoResponseDto> execute() {
        return WebClient.create()
                .get()
                .uri(properties.getCryptoUrl())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(CryptoResponseDto.class)
                .onErrorMap(e -> new ErrorInCallToApiException("Error during call in API" + e.getMessage()));
    }


}
