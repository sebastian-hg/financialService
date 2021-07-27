package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.impl.GetBitcoinPriceAndSaveToDataBaseServiceImpl;
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

@ExtendWith(SpringExtension.class)
class CallTimedToPriceBtcServiceTest {

    @Mock
    private GetCryptoPriceService priceService;
    private CryptoHistoryPriceRepository priceRepository;
    @InjectMocks
    private GetBitcoinPriceAndSaveToDataBaseServiceImpl service;
    private Crypto crypto;

    @BeforeEach
    void setup() {
        crypto = Crypto.builder()
                .name("btc")
                .id(1L)
                .build();
    }

    @Test
    void whenExecuteThenExecuteIsAOk() throws Exception {
        givenAService();
        givenARepository();
        whenExecute();
        thenExecuteIsAOk();


    }

    private void givenAService() throws Exception {
        Mockito.when(priceService.getBtcPrice()).thenReturn(Mono.just(CryptoHistoryPrice.builder()
                .createdAt(LocalDateTime.now())
                .price(150.00)
                .id(1L)
                .crypto(crypto)
                .build()));
    }

    private void givenARepository() {
        var cryptoHistoryPrice = CryptoHistoryPrice.builder()
                .price(150.00)
                .id(1L)
                .createdAt(LocalDateTime.now())
                .crypto(crypto)
                .build();
        Mockito.when(priceRepository.save(any())).thenReturn(cryptoHistoryPrice);

    }

    private void whenExecute() throws Exception {
        service.execute();

    }

    private void thenExecuteIsAOk() throws Exception {
        Mockito.verify(priceService).getBtcPrice();
        Mockito.verify(priceRepository).save(any());
    }

}
