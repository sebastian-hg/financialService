package com.mycompany.financialservices.service.impl;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.model.response.ViewAverageBitcoinResponse;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.CalculateAverageOfCryptoCurrenciesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Log4j2
public class CalculateAverageOfCryptoCurrenciesServiceImpl implements CalculateAverageOfCryptoCurrenciesService {
    private final CryptoHistoryPriceRepository priceRepository;


    @Override
    public Mono<ViewAverageBitcoinResponse> execute(LocalDateTime init, LocalDateTime end, Long cryptoId) {

        Double priceForCycle;
        Double priceSun = 0d;
        var listResult = priceRepository.findAllByCryptoIdAndCreatedAtBetween(cryptoId, init, end);
        for (CryptoHistoryPrice cryptoHistoryPrice : listResult) {
            priceForCycle = cryptoHistoryPrice.getPrice();
            priceSun += priceForCycle;
        }
        Double average = priceSun / listResult.size();
        return Mono.just(ViewAverageBitcoinResponse.builder()
                .averagePrice(average)
                .build());
    }

    @Override
    public Mono<ViewAverageBitcoinResponse> executeQuery(Long cryptoId, LocalDateTime init, LocalDateTime end) {
        var averageQuery = priceRepository.findAverage(cryptoId, init, end);
        return Mono.just(ViewAverageBitcoinResponse.builder()
                .averagePrice(averageQuery)
                .build());
    }

}
