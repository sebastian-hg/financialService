package com.mycompany.financialservices.repository;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CryptoHistoryPriceRepository extends JpaRepository<CryptoHistoryPrice, Long> {
    List<CryptoHistoryPrice> findAllByCryptoIdAndCreatedAtBetween(Long cryptoId, LocalDateTime init, LocalDateTime
            end);

//    @Query("SELECT c FROM CryptoHistoryPrice c where c.cryptoId=? and c.createdAt BETWEEN ? AND ?")

//    Double findAverage(Long cryptoId, LocalDateTime init, LocalDateTime end);
    //findAllByCrytoAndByCreatedAtBetween(Crypto crypto, LocalDateTime inicio, LocalDateTime fin)

}
