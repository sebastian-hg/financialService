package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
class GetBitcoinPriceAndSaveToDataBaseServiceTest {

    private CryptoHistoryPrice cryptoHistoryPrice;
    @Mock
    private CryptoHistoryPriceRepository cryptoHistoryPriceRepository;
    @Mock
    private GetCryptoPriceService getCryptoPriceService;
    @InjectMocks
    private GetBitcoinPriceAndSaveToDataBaseServiceImpl service;


    @BeforeEach
    void setup() {
    }

    @Test
    void whenExecuteThenExecuteIsAOk() throws Exception {
        givenAService();
        givenARepository();
        whenExecute();
        thenExecuteIsAOk();


    }

    private void givenAService() throws Exception {
        Crypto crypto = Crypto.builder()
                .name("btc")
                .id(1L)
                .build();
        cryptoHistoryPrice = CryptoHistoryPrice.builder()
                .createdAt(LocalDateTime.now())
                .price(150.00)
                .id(1L)
                .crypto(crypto)
                .build();
        Mockito.when(getCryptoPriceService.execute(anyString())).thenReturn(Mono.just(cryptoHistoryPrice));
    }

    private void givenARepository() {
        Mockito.when(cryptoHistoryPriceRepository.save(any(CryptoHistoryPrice.class))).thenReturn(cryptoHistoryPrice);

    }

    private void whenExecute() throws Exception {
        service.execute();
    }

    private void thenExecuteIsAOk() throws Exception {
        Mockito.verify(getCryptoPriceService).execute(anyString());
        Mockito.verify(cryptoHistoryPriceRepository).save(any());
    }

}
