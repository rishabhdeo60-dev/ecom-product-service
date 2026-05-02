package com.rdkp63.ecom_product_services.repository;

import com.rdkp63.ecom_product_services.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByCreatedBy(Long createdBy);

    Optional<Product> findByIdAndCreatedBy(Long id, Long createdBy);

    boolean existsByCreatedByAndName(Long createdBy, String name);
}
