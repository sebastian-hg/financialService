package com.mycompany.financialservices.service.impl;

import com.mycompany.financialservices.client.CryptoClient;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.repository.CryptoRepository;
import com.mycompany.financialservices.service.GetCryptoPriceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Log4j2
public class GetCryptoPriceServiceImpl implements GetCryptoPriceService {
    private final CryptoClient client;
    private final CryptoRepository cryptoRepository;


    @Override
    public Mono<CryptoHistoryPrice> getBtcPrice() throws Exception {
        var crypto = cryptoRepository.findByName("BTC").orElseThrow(Exception::new);
        return client.execute().map(cryptoResponseDto ->
        {
            //log.info("result  of record in th call method:  {}", cryptoResponseDto);
            return CryptoHistoryPrice.builder()
                    .crypto(crypto)
                    .price(cryptoResponseDto.getObject().getBtcArs().getPurchasePrice())
                    .createdAt(LocalDateTime.now())
                    .build();
        });
    }

    @Override
    public Mono<CryptoHistoryPrice> getEthPrice() throws Exception {
        var cryptoEth = cryptoRepository.findByName("ETH").orElseThrow(Exception::new);
        return client.execute().map(cryptoResponseDto -> {
            return CryptoHistoryPrice.builder()
                    .crypto(cryptoEth)
                    .price(cryptoResponseDto.getObject().getEthArs().getPurchasePrice())
                    .createdAt(LocalDateTime.now())
                    .build();

        });
    }

    @Override
    public Mono<CryptoHistoryPrice> getDaiPrice() throws Exception {
        var cryptoDai = cryptoRepository.findByName("DAI").orElseThrow(Exception::new);
        return client.execute().map(cryptoResponseDto -> {
            return CryptoHistoryPrice.builder()
                    .crypto(cryptoDai)
                    .price(cryptoResponseDto.getObject().getDaiArs().getPurchasePrice())
                    .createdAt(LocalDateTime.now())
                    .build();

        });
    }
}
