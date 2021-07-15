package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.model.response.ViewAverageBitcoinResponse;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.impl.CalculateAverageOfCryptoCurrenciesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class CalculateAverageOfCryptoCurrenciesTest {
    @Mock
    private CryptoHistoryPriceRepository cryptoHistoryPriceRepository;
    @InjectMocks
    private CalculateAverageOfCryptoCurrenciesServiceImpl calculateAverageService;
    private ViewAverageBitcoinResponse viewAverageBitcoinResponse;
    private List<CryptoHistoryPrice> cryptoHistoryPriceList;

    @Test
    void whenFindAllByCryptoIdAndCreatedAtBetweenThenIsAOk() {
        givenFindAllByCryptoIdAndCreatedAtBetweenARepository();
        givenResponse();
        whenFindAllByCryptoIdAndCreatedAtBetween();
        thenIsAOk();
    }

    private void givenFindAllByCryptoIdAndCreatedAtBetweenARepository() {
        Mono<ViewAverageBitcoinResponse> responseMono
                = Mono.just(ViewAverageBitcoinResponse.builder().averagePrice(60000.00).build());
        Mockito.when(cryptoHistoryPriceRepository.)
    }

    private void givenResponse() {

    }

    private void whenFindAllByCryptoIdAndCreatedAtBetween() {

    }

    private void thenIsAOk() {

    }

    ;


}
