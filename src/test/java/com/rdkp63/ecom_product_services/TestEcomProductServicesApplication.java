package com.rdkp63.ecom_product_services;

import org.springframework.boot.SpringApplication;

public class TestEcomProductServicesApplication {

	public static void main(String[] args) {
		SpringApplication.from(EcomProductServicesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
