package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Product;
import com.youcode.sudest_market.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByNameAndStoreId(String name, UUID id);

    Optional<Product> findBySlug(String slug);

    List<Product> findByStore(Store store);
}