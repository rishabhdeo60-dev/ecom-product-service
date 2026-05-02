package com.rdkp63.ecom_product_services.dto.request;

import com.rdkp63.ecom_product_services.util.enums.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class UpdateProductRequest {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Currency currency;
}
