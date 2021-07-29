package com.mycompany.financialservices.service;

import com.mycompany.financialservices.client.CryptoClient;
import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.model.dto.response.CryptoDto;
import com.mycompany.financialservices.model.dto.response.CryptoResponseDto;
import com.mycompany.financialservices.model.dto.response.ObjectDto;
import com.mycompany.financialservices.repository.CryptoRepository;
import com.mycompany.financialservices.service.impl.GetCryptoPriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class GetPriceCryptoCurrenciesTest {
    @Mock
    private CryptoClient client;
    private CryptoRepository repository;
    @InjectMocks
    private GetCryptoPriceServiceImpl service;
    private Mono<CryptoResponseDto> cryptoResponseDtoMono;
    private Optional<Crypto> crypto;
    private CryptoResponseDto expected;
    private Mono<CryptoHistoryPrice> response;
    private Crypto cryptoCurrencies;

    @BeforeEach
    void setup() {
        crypto = Optional.of(Crypto
                .builder()
                .name("btc")
                .id(1L)
                .build()
        );
        cryptoCurrencies = Crypto.builder()
                .id(1L)
                .name("btc")
                .build();
        cryptoResponseDtoMono = Mono.just(CryptoResponseDto.builder()
                .object(ObjectDto.builder().btcArs(CryptoDto.builder().purchasePrice(500.00).build()).build()).build());
    }

    @Test
    void whenGetBtcPriceThemIsAOK() throws Exception {
        givenARepository();
        givenAClient();
        givenResponse();
        whenGetBtcPrice();
        themIsAOk();
    }

    private void givenARepository() throws Exception {
        Mockito.when(repository.findByName("name").orElseThrow(Exception::new)).thenReturn(cryptoCurrencies);
    }

    private void givenAClient() {
        Mockito.when(client.execute()).thenReturn(cryptoResponseDtoMono);
    }

    private void givenResponse() {
        expected = CryptoResponseDto.builder()
                .object(ObjectDto.builder().btcArs(CryptoDto.builder().purchasePrice(60.00).build()).build())
                .build();
    }

    private void whenGetBtcPrice() throws Exception {
        response = service.execute("BTC");
    }

    private void themIsAOk() {
        StepVerifier.create(response)
                .expectNextMatches(CryptoHistoryPrice -> CryptoHistoryPrice.equals(expected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).findByName("btc");
        Mockito.verify(client).execute();
    }
}
