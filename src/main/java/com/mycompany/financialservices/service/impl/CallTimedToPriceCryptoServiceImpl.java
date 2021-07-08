package com.mycompany.financialservices.service.impl;

import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.service.CallTimedToPriceCryptoService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Log4j2
public class CallTimedToPriceCryptoServiceImpl implements CallTimedToPriceCryptoService {
    private final CryptoHistoryPriceServiceImpl priceCryptoService;
    private final CryptoHistoryPriceRepository cryptoHistoryRepository;

    @Override
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void execute() throws Exception {
        log.info("se ejecuto el metodo");
        priceCryptoService.getBtcPrice().map(cryptoHistoryRepository::save).subscribe();
    }
}
