package com.mycompany.financialservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class CryptoHistoryPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id = 0L;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;
    private Double price;
    private LocalDateTime createdAt;

    //TODO: MANY TO MANY , ONETOONE;

}
