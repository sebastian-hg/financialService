package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.model.response.ViewAverageBitcoinResponse;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.impl.GetCryptoAverageByDateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class GetCryptoAverageByDateServiceTest {
    @Mock
    private CryptoHistoryPriceRepository repository;
    @InjectMocks
    private GetCryptoAverageByDateServiceImpl service;
    private ViewAverageBitcoinResponse expectedResponse;
    private LocalDateTime initRequest;
    private LocalDateTime endRequest;
    private Long idRequest;
    private Mono<ViewAverageBitcoinResponse> response;

    @Test
    void givenRequestWhenExecuteThenExecuteIsAOk() {
        givenRequest();
        givenRepositories();
        givenResponse();
        whenExecute();
        thenExecuteIsAOk();
    }

    private void givenRequest() {
        idRequest = 1L;
        initRequest = LocalDateTime.now().minusDays(10);
        endRequest = LocalDateTime.now();
    }

    private void givenRepositories() {
        List<CryptoHistoryPrice> cryptoHistoryPriceList = new ArrayList<>();
        Crypto crypto = Crypto.builder()
                .name("btc")
                .id(1L)
                .build();
        CryptoHistoryPrice price = CryptoHistoryPrice.builder()
                .id(1L)
                .crypto(crypto)
                .createdAt(LocalDateTime.now().minusDays(15L))
                .price(50000.00)
                .build();
        CryptoHistoryPrice price2 = CryptoHistoryPrice.builder()
                .id(1L)
                .crypto(crypto)
                .createdAt(LocalDateTime.now())
                .price(100000.00)
                .build();
        cryptoHistoryPriceList.add(price);
        cryptoHistoryPriceList.add(price2);
        Mockito.when(repository.findAllByCryptoIdAndCreatedAtBetween(idRequest, initRequest, endRequest))
                .thenReturn(cryptoHistoryPriceList);
    }

    private void givenResponse() {
        expectedResponse = ViewAverageBitcoinResponse.builder()
                .averagePrice(75000.00)
                .build();
    }

    private void whenExecute() {
        response = service.execute(initRequest, endRequest, idRequest);
    }

    private void thenExecuteIsAOk() {
        StepVerifier.create(response)
                .expectNextMatches(averageBitcoinResponse -> averageBitcoinResponse.equals(expectedResponse))
                .expectComplete()
                .verify();
        Mockito.verify(repository).findAllByCryptoIdAndCreatedAtBetween(idRequest, initRequest, endRequest);
    }

    @Test
    void givenRequestWhenExecuteQueryThenExecuteQueryIsAOk() {
        givenRequestExecuteQuery();
        givenRepositoryExecuteQuery();
        givenResponseExecuteQuery();
        whenExecuteQuery();
        thenExecuteQueryIsAOk();
    }

    private void givenRequestExecuteQuery() {
        idRequest = 1L;
        initRequest = LocalDateTime.now().minusDays(20L);
        endRequest = LocalDateTime.now();
    }

    private void givenRepositoryExecuteQuery() {
        Double doubleResult = 500.00;
        Mockito.when(repository.findAverageBetween(idRequest, initRequest, endRequest)).thenReturn(doubleResult);
    }

    private void givenResponseExecuteQuery() {
        expectedResponse = ViewAverageBitcoinResponse.builder()
                .averagePrice(500.00)
                .build();
    }

    private void whenExecuteQuery() {
        response = service.executeQuery(idRequest, initRequest, endRequest);
    }

    private void thenExecuteQueryIsAOk() {

        StepVerifier.create(response)
                .expectNextMatches(averageBitcoinResponse -> averageBitcoinResponse.equals(expectedResponse))
                .expectComplete()
                .verify();
        Mockito.verify(repository).findAverageBetween(idRequest, initRequest, endRequest);
    }
}
