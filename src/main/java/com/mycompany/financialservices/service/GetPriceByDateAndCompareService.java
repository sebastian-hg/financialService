package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.response.ViewPriceByDateAndCompareByDatesResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@FunctionalInterface
public interface GetPriceByDateAndCompareService {
    Mono<ViewPriceByDateAndCompareByDatesResponse> execute(Long cryptoId, LocalDateTime star, LocalDateTime end);
}
