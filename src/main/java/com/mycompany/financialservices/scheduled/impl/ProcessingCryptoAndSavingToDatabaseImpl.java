package com.mycompany.financialservices.scheduled.impl;

import com.mycompany.financialservices.repository.CryptoHistoryPriceRepository;
import com.mycompany.financialservices.scheduled.ProcessingCryptoAndSavingToDatabase;
import com.mycompany.financialservices.service.GetCryptoPriceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@AllArgsConstructor
public class ProcessingCryptoAndSavingToDatabaseImpl implements ProcessingCryptoAndSavingToDatabase {
    private GetCryptoPriceService service;
    private CryptoHistoryPriceRepository repository;

    @Override
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void execute() throws Exception {
        List <String> cryptoDataBase = null;
        String eth= "ETH";
        String btc = "BTC";
        String dai = "DAI";
        cryptoDataBase.add(eth);
        cryptoDataBase.add(btc);
        cryptoDataBase.add(dai);
        for (String x: cryptoDataBase){
            service.execute(x);

        }



    }

}
