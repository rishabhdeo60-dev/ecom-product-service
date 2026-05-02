package com.rdkp63.ecom_product_services.dto.request;

import com.rdkp63.ecom_product_services.util.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class CreateProductRequest {

    @NotBlank
    private String productName;

    private String productDescription;

    @NotNull
    @Positive
    private BigDecimal productPrice;

    @Builder.Default
    private Currency currency = Currency.INR;
}
