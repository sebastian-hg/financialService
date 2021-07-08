package com.mycompany.financialservices.repository;

import com.mycompany.financialservices.model.Crypto;
import com.mycompany.financialservices.model.CryptoHistoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
    Optional <Crypto> findByName(String name);

}
