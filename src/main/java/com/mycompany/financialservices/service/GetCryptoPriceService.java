package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import reactor.core.publisher.Mono;


public interface GetCryptoPriceService {

    Mono<CryptoHistoryPrice> execute(String crypto) throws Exception;

}
