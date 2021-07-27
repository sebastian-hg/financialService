package com.mycompany.financialservices.controller;

import com.mycompany.financialservices.model.response.ViewPriceByDateAndCompareByDatesResponse;
import com.mycompany.financialservices.service.GetPriceByDateAndCompareService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/financial/crypto/price/date")
public class GetPriceByDateController {
    private final GetPriceByDateAndCompareService getPriceByDateService;

    @GetMapping
    public Mono<ViewPriceByDateAndCompareByDatesResponse> viewPrice(@RequestParam("id") Long id, @RequestParam
            ("star") String star, @RequestParam("end") String end) {
        var starDay = LocalDateTime.parse(star);
        var endDay = LocalDateTime.parse(end);
        return getPriceByDateService.execute(id,starDay,endDay);
    }
}
