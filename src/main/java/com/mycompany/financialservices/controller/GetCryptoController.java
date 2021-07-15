package com.mycompany.financialservices.controller;

import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.service.CryptoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/financial/bitcoin/")
public class GetCryptoController {

    private final CryptoService service;

    @GetMapping()
    public Mono<Crypto> viewBtc()  {
        return service.execute();
    }

}
