package com.rdkp63.ecom_product_services.controller;

import com.rdkp63.ecom_product_services.dto.request.CreateProductRequest;
import com.rdkp63.ecom_product_services.dto.request.ProductDeleteRequest;
import com.rdkp63.ecom_product_services.dto.request.UpdateProductRequest;
import com.rdkp63.ecom_product_services.dto.response.ProductDeleteResponse;
import com.rdkp63.ecom_product_services.dto.response.ProductResponse;
import com.rdkp63.ecom_product_services.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/my")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return productService.myProducts();
    }

    @PatchMapping("/update")
    public  ResponseEntity<ProductResponse> updateProduct(@RequestBody UpdateProductRequest request){
        return productService.updateProduct(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ProductDeleteResponse> deleteProduct(@RequestBody ProductDeleteRequest request) {
        return productService.deleteProduct(request);
    }
}
