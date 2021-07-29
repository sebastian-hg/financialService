package com.mycompany.financialservices.controller;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import com.mycompany.financialservices.service.impl.GetCryptoPriceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/financial/Crypto/price/")
public class GetPriceController {
    private final GetCryptoPriceServiceImpl priceService;

    @GetMapping("/bitcoin/")
    public Mono<CryptoHistoryPrice> viewPrice() throws Exception {
        return priceService.execute("BTC");
    }

    @GetMapping("/ethereum/")
    public Mono<CryptoHistoryPrice> viewPriceEth() throws Exception {
        return priceService.execute("ETH");
    }

    @GetMapping("/dai/")
    public Mono<CryptoHistoryPrice> viewPriceDai() throws Exception {
        return priceService.execute("DAI");
    }


}
