package com.mycompany.financialservices.service.impl;

import com.mycompany.financialservices.client.CryptoClient;
import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.service.CryptoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CryptoBtcServiceImpl implements CryptoService {
    private final CryptoClient client;


    @Override
    public Mono<Crypto> execute() {
        return client.execute().map(cryptoResponseDto -> {
            Crypto crypto= Crypto.builder()
                    .name(cryptoResponseDto.getObject().getBtcArs().getBidCurrency())
                    .build();
            return crypto;
        });
    }


}
