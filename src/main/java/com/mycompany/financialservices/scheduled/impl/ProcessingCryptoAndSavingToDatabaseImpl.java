package com.mycompany.financialservices.scheduled.impl;

import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.scheduled.ProcessingCryptoAndSavingToDatabase;
import com.mycompany.financialservices.service.GetCryptoPriceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Service
public class ProcessingCryptoAndSavingToDatabaseImpl implements ProcessingCryptoAndSavingToDatabase {
    private GetCryptoPriceService service;
    private CryptoHistoryPriceRepository repository;


    @Override
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void execute() throws Exception {
        List<String> cryptoDataBase = new ArrayList<>();
        String ETH = "ETH";
        String BTC = "BTC";
        String DAI = "DAI";
        cryptoDataBase.add(BTC);
        cryptoDataBase.add(DAI);
        cryptoDataBase.add(ETH);
        for (String x : cryptoDataBase) {
            service.execute(x).map(repository::save).subscribe();
            log.info("se ejecuto metodo :" + x);
        }
        log.info("se ejecuto metodo :" );
    }

}
