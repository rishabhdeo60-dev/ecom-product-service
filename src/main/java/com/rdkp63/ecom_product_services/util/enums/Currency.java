package com.rdkp63.ecom_product_services.util.enums;

import lombok.Getter;

@Getter
public enum Currency {
    USD("USD", "United States Dollar"),
    EUR("EUR", "Euro"),
    INR("INR", "Indian Rupee");

    private final String code;
    private final String description;

    Currency(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
