package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.Product;
import com.youcode.sudest_market.domain.Store;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findByStore(Store store);
    Product findById(UUID id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    boolean deleteProduct(UUID id);
    List<Product> filterByCategoryAndStore(String categoryName, Store store);
    List<Product> filterByCategory(String categoryName);

    void validateProduct(Product product, Product existingProduct);

    Product findBySlug(String slug);
}
