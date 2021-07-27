package com.mycompany.financialservices.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ViewPriceByDateAndCompareByDatesResponse {
    Double priceInitial;
    Double priceFinal;
    Double fluctuatingPercentage;


}
