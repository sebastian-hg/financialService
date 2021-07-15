package com.mycompany.financialservices.repository;

import com.mycompany.financialservices.model.CryptoHistoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CryptoHistoryPriceRepository extends JpaRepository<CryptoHistoryPrice, Long> {
    List<CryptoHistoryPrice> findAllByCryptoIdAndCreatedAtBetween(Long cryptoId, LocalDateTime init, LocalDateTime
            end);

    @Query(value = "SELECT AVG(c.price) FROM crypto_history_price c  where c.crypto_id=?1 and c.created_at " +
            "BETWEEN ?2 AND " + "?3", nativeQuery = true)
    Double findAverage(Long cryptoId, LocalDateTime init, LocalDateTime end);


}
