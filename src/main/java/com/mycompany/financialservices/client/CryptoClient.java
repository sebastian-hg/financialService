package com.mycompany.financialservices.client;

import com.mycompany.financialservices.model.dto.response.CryptoResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CryptoClient {

    Mono<CryptoResponseDto> execute();

}
