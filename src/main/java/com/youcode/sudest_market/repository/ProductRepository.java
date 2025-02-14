package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByNameAndStoreId(String name, UUID id);

    Optional<Product> findBySlug(String slug);
}