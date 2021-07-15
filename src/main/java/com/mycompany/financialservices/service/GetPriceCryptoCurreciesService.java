package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import reactor.core.publisher.Mono;

public interface GetPriceCryptoCurreciesService {
    Mono<CryptoHistoryPrice> getBtcPrice() throws Exception;

    Mono<CryptoHistoryPrice> getEthPrice();

    Mono<CryptoHistoryPrice> getDaiPrice();
}
