package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import reactor.core.publisher.Mono;

public interface GetCryptoPriceService {
    Mono<CryptoHistoryPrice> getBtcPrice() throws Exception;

    Mono<CryptoHistoryPrice> getEthPrice() throws Exception;

    Mono<CryptoHistoryPrice> getDaiPrice() throws Exception;
}
