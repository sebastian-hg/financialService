package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.response.ViewPriceByDateAndCompareByDatesResponse;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.impl.GetPriceByDateAndCompareServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
class GetPriceByDateServiceAndCompareTest {
    @Mock
    private CryptoHistoryPriceRepository repository;
    @InjectMocks
    private GetPriceByDateAndCompareServiceImpl service;
    private LocalDateTime star;
    private LocalDateTime end;
    private Long id;
    private Double responseInitial;
    private Double responseFinal;
    private ViewPriceByDateAndCompareByDatesResponse expected;
    private Mono<ViewPriceByDateAndCompareByDatesResponse> responseService;

    @Test
    void givenRequestWhenExecuteThenExecuteThemIsAOk() {
        givenRequest();
        givenRepository();
        givenResponse();
        whenExecute();
        thenExecuteIsAOk();
    }

    private void givenRequest() {
        end = LocalDateTime.now();
        star = LocalDateTime.now().minusDays(5);
        id = 1L;
    }

    private void givenRepository() {
        responseInitial = 10.00;
        responseFinal = 11.00;
        Mockito.when(repository.searchPriceByDateStar(1L, star)).thenReturn(responseInitial);
        Mockito.when(repository.searchPriceByDateEnd(1L, end)).thenReturn(responseFinal);
    }

    private void givenResponse() {
        expected = ViewPriceByDateAndCompareByDatesResponse.builder()
                .priceInitial(10.00)
                .priceFinal(11.00)
                .fluctuatingPercentage(10.0)
                .build();
    }

    private void whenExecute() {
        responseService = service.execute(id, star, end);
    }

    private void thenExecuteIsAOk() {
        StepVerifier.create(responseService)
                .expectNextMatches(viewPriceByDateAndCompareByDatesResponse ->
                        viewPriceByDateAndCompareByDatesResponse.equals(expected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).searchPriceByDateStar(1L, star);
        Mockito.verify(repository).searchPriceByDateEnd(1L, end);
    }


}
