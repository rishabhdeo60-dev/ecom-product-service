package com.rdkp63.ecom_product_services.service;

import com.rdkp63.ecom_product_services.dto.request.CreateProductRequest;
import com.rdkp63.ecom_product_services.dto.request.DisableProductsRequest;
import com.rdkp63.ecom_product_services.dto.request.ProductDeleteRequest;
import com.rdkp63.ecom_product_services.dto.request.UpdateProductRequest;
import com.rdkp63.ecom_product_services.dto.response.ProductDeleteResponse;
import com.rdkp63.ecom_product_services.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    ResponseEntity<ProductResponse> createProduct(CreateProductRequest request);

    ResponseEntity<List<ProductResponse>> myProducts();

    ResponseEntity<ProductResponse> updateProduct(UpdateProductRequest request);

    ResponseEntity<ProductDeleteResponse> deleteProduct(ProductDeleteRequest request);

    ResponseEntity<List<ProductResponse>> disableProducts(DisableProductsRequest request);
}
