package com.mycompany.financialservices.service;

import com.mycompany.financialservices.model.response.ViewAverageBitcoinResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface CalculateAverageOfCryptoCurrenciesService {
    Mono<ViewAverageBitcoinResponse> execute(LocalDateTime init, LocalDateTime end, Long cryptoId);

    Mono<ViewAverageBitcoinResponse> executeQuery( Long cryptoId,LocalDateTime init, LocalDateTime end);

}
