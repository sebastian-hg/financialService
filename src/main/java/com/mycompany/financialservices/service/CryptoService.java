package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.Crypto;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface CryptoService {
    Mono<Crypto>execute();

}
