package com.mycompany.financialservices.service.impl;

import com.mycompany.financialservices.model.response.ViewPriceByDateAndCompareByDatesResponse;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.GetPriceByDateAndCompareService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class GetPriceByDateAndCompareServiceImpl implements GetPriceByDateAndCompareService {
    private CryptoHistoryPriceRepository repository;


    @Override
    public Mono<ViewPriceByDateAndCompareByDatesResponse> execute(Long cryptoId, LocalDateTime star, LocalDateTime end) {
        var initialValue = repository.searchPriceByDateStar(cryptoId, star);
        var endValue = repository.searchPriceByDateEnd(cryptoId, end);
       // Double valueDifference = initialValue - endValue;
        Double percent = endValue * 100 / initialValue - 100;

        return Mono.just(ViewPriceByDateAndCompareByDatesResponse.builder()
                .priceInitial(initialValue)
                .priceFinal(endValue)
                .fluctuatingPercentage(percent)
                .build());
    }
}
