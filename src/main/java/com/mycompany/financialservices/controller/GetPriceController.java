package com.mycompany.financialservices.controller;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.service.impl.GetPriceCryptoCurrenciesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/financial/Crypto/price/")
public class GetPriceController {
    private final GetPriceCryptoCurrenciesServiceImpl priceService;

    @GetMapping("/bitcoin/")
    public Mono<CryptoHistoryPrice> viewPrice() throws Exception {return priceService.getBtcPrice();}

    @GetMapping("/ethereum/")
    public Mono<CryptoHistoryPrice> viewPriceEth(){return priceService.getEthPrice();}

    @GetMapping("/dai/")
    public Mono<CryptoHistoryPrice> viewPriceDai(){return priceService.getDaiPrice();}


}
