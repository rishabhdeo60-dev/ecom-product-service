package com.rdkp63.ecom_product_services.service.impl;

import com.rdkp63.ecom_product_services.dto.request.CreateProductRequest;
import com.rdkp63.ecom_product_services.dto.request.DisableProductsRequest;
import com.rdkp63.ecom_product_services.dto.request.ProductDeleteRequest;
import com.rdkp63.ecom_product_services.dto.request.UpdateProductRequest;
import com.rdkp63.ecom_product_services.dto.response.ProductDeleteResponse;
import com.rdkp63.ecom_product_services.dto.response.ProductResponse;
import com.rdkp63.ecom_product_services.entity.Product;
import com.rdkp63.ecom_product_services.repository.ProductRepository;
import com.rdkp63.ecom_product_services.service.ProductService;
import com.rdkp63.ecom_product_services.util.context.UserContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;
    private final UserContext userContext;

    @Override
    public ResponseEntity<ProductResponse> createProduct(CreateProductRequest request) {

        // check if product already exists by name and created by
        if(productRepository.existsByCreatedByAndName(userContext.getUserId(), request.getProductName())){
            throw new RuntimeException("Product with name "+ request.getProductName()+" already exists");
        }

        Product product = Product.builder()
                .name(request.getProductName())
                .description(request.getProductDescription())
                .price(request.getProductPrice())
                .currency(request.getCurrency())
                .active(true)
                .createdAt(LocalDateTime.now())
                .createdBy(userContext.getUserId())
                .build();

        Product saved = productRepository.save(product);

        URI URI_location = URI.create("/api/v1/product/" + saved.getId());

        ProductResponse response = toResponse(saved);
        return ResponseEntity.created(URI_location).body(response);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> myProducts() {

        List<Product> products = productRepository.findByCreatedBy(userContext.getUserId())
                .orElseThrow(() -> new RuntimeException("No products found with userID"));

        List<ProductResponse> response = products.stream().map(this::toResponse).toList();

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(UpdateProductRequest request) {

        Long userId = userContext.getUserId();
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("No product found with userID"));
        setIfNotNull(request.getName(), product::setName);
        setIfNotNull(request.getDescription(), product::setDescription);
        setIfNotNull(request.getPrice(), product::setPrice);
        setIfNotNull(request.getCurrency(), product::setCurrency);
        setIfNotNull(userId, product::setUpdatedBy);

        ProductResponse response = null;
        try {
            Product saved = productRepository.save(product);
            response = toResponse(saved);
        } catch(DataIntegrityViolationException e) {
            throw new RuntimeException("Product with name "+ product.getName()+" already exists");
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProductDeleteResponse> deleteProduct(ProductDeleteRequest request) {

        Product foundProduct = productRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("No product found with id: " + request.getId()));

        productRepository.delete(foundProduct);
        return null;
    }

    @Override
    public ResponseEntity<List<ProductResponse>> disableProducts(DisableProductsRequest request) {
        return null;
    }

    // ------------------ Helper Methods ---------------

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .active(product.getActive())
                .build();
    }

    private <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
