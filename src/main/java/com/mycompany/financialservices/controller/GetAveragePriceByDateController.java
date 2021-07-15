package com.mycompany.financialservices.controller;

import com.mycompany.financialservices.model.response.ViewAverageBitcoinResponse;
import com.mycompany.financialservices.service.impl.CalculateAverageOfCryptoCurrenciesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/financial/GetAveragePrice")
public class GetAveragePriceByDateController {
    private final CalculateAverageOfCryptoCurrenciesServiceImpl calculateAverageOfCryptoCurrenciesService;

    @GetMapping
    public Mono<ViewAverageBitcoinResponse> viewAverageBitcoin(@RequestParam("id") Long id, @RequestParam
            ("init") String init, @RequestParam("end") String end) {
        var starDay = LocalDateTime.parse(init);
        var endDay = LocalDateTime.parse(end);
        return calculateAverageOfCryptoCurrenciesService.execute(starDay, endDay, id);
    }

    @GetMapping("/query")
    public Mono<ViewAverageBitcoinResponse> viewAverageBitcoinByQuery
            (@RequestParam("id") Long id, @RequestParam("star") String star,
             @RequestParam("end") String end) {
        var starDay = LocalDateTime.parse(star);
        var endDay = LocalDateTime.parse(end);
        return calculateAverageOfCryptoCurrenciesService.executeQuery(id, starDay, endDay);
    }


}
