package com.mycompany.financialservices.service.impl;

import com.mycompany.financialservices.client.CryptoClient;
import com.mycompany.financialservices.exception.ErrorInCallToApiException;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.model.dto.response.CryptoResponseDto;
import com.mycompany.financialservices.repository.CryptoRepository;
import com.mycompany.financialservices.service.GetCryptoPriceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Service
@Log4j2
public class GetCryptoPriceServiceImpl implements GetCryptoPriceService {
    private final CryptoClient client;
    private final CryptoRepository cryptoRepository;


    @Override
    public Mono<CryptoHistoryPrice> execute(String crypto) throws Exception {
        return client.execute()
                .flatMap(cryptoResponseDto -> buildCryptoHistoryPrice(cryptoResponseDto, crypto));
    }

    private Mono<CryptoHistoryPrice> buildCryptoHistoryPrice(CryptoResponseDto cryptoResponseDto, String crypto) {
        var cryptoCurrencies = cryptoRepository.findByName(crypto);
        if (cryptoCurrencies.isEmpty())
            return Mono.error(new ErrorInCallToApiException("error"));
        Double price;
        if (crypto.equalsIgnoreCase("btc")) {
            price = cryptoResponseDto.getObject().getBtcArs().getPurchasePrice();
        } else if (crypto.equalsIgnoreCase("eth")) {
            price = cryptoResponseDto.getObject().getEthArs().getPurchasePrice();
        } else if (crypto.equalsIgnoreCase("dai")) {
            price = cryptoResponseDto.getObject().getDaiArs().getPurchasePrice();
        } else {
            return Mono.error(new ErrorInCallToApiException("crypto no exist"));
        }
        var result = CryptoHistoryPrice.builder()
                .crypto(cryptoCurrencies.get())
                .price(price)
                .createdAt(LocalDateTime.now())
                .build();
        return Mono.just(result);
    }
}

