package com.mycompany.financialservices.service;

import com.mycompany.financialservices.client.CryptoClient;
import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.model.dto.response.CryptoDto;
import com.mycompany.financialservices.model.dto.response.CryptoResponseDto;
import com.mycompany.financialservices.model.dto.response.ObjectDto;
import com.mycompany.financialservices.service.impl.GetBtcTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class GetBtcTypeServiceTest {
    @Mock
    private CryptoClient client;
    @InjectMocks
    private GetBtcTypeServiceImpl btcService;
    private Crypto expectedResponse;
    private Mono<Crypto> response;
    private Mono<CryptoResponseDto> dtoMono;

    @Test
    void WhenExecuteThenExecuteIsAOK() {
        givenAClient();
        givenResponse();
        givenExecute();
        thenExecuteIsAOk();
    }

    private void givenAClient() {
        dtoMono = Mono.just(CryptoResponseDto.builder()
                .object(ObjectDto.builder()
                        .btcArs(CryptoDto.builder()
                                .bidCurrency("btc").build())
                        .build())
                .build());
        Mockito.when(client.execute()).thenReturn(dtoMono);
    }

    private void givenResponse() {
        expectedResponse = Crypto.builder()
                .name("btc")
                .build();
    }

    private void givenExecute() {
        response = btcService.execute();
    }

    private void thenExecuteIsAOk() {
        StepVerifier.create(response)
                .expectNextMatches(Crypto -> Crypto.equals(expectedResponse))
                .expectComplete()
                .verify();
        Mockito.verify(client).execute();
    }
}
