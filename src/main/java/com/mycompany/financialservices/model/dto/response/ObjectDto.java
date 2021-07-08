package com.mycompany.financialservices.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ObjectDto {
    @JsonProperty("daiars")
    private CryptoDto daiArs;
    @JsonProperty("btcdai")
    private CryptoDto btcDai;
    @JsonProperty("daiusd")
    private CryptoDto daiUsd;
    @JsonProperty("ethars")
    private CryptoDto ethArs;
    @JsonProperty("btcars")
    private CryptoDto btcArs;
    @JsonProperty("ethdai")
    private CryptoDto ethDai;

}
