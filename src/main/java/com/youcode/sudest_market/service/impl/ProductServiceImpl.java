package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.Product;
import com.youcode.sudest_market.domain.Store;
import com.youcode.sudest_market.domain.enums.ProductStatus;
import com.youcode.sudest_market.exception.NotValidConstraintException;
import com.youcode.sudest_market.exception.ResourceNotFoundException;
import com.youcode.sudest_market.repository.ProductRepository;
import com.youcode.sudest_market.service.AuthService;
import com.youcode.sudest_market.service.CategoryService;
import com.youcode.sudest_market.service.ProductService;
import com.youcode.sudest_market.service.StoreService;
import com.youcode.sudest_market.util.SlugUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuthService authService;
    private final StoreService storeService;
    private final CategoryService categoryService;

    @Override
    public List<Product> findByStore(Store store) {
        return null;
    }

    @Override
    public Product findById(UUID id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        // Set product store
        Store store = this.authService.getAuthenticatedUser().getStore();
        product.setStore(store);

        // Validate product
        validateProduct(product, null);

        // Set creation metadata
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.ACTIVE);
        }

        if (product.getSlug() == null || product.getSlug().isEmpty()) {
            product.setSlug(SlugUtils.generateSlug(product.getName()));
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        // Set product store
        Store store = this.authService.getAuthenticatedUser().getStore();
        product.setStore(store);

        // Find existing product
        Product existingProduct = productRepository.findBySlug(product.getSlug())
                .orElseThrow(() -> new ResourceNotFoundException("Product"));

        validateProduct(product, existingProduct);

        // Set update metadata
        product.setUpdatedAt(LocalDateTime.now());
        product.setId(existingProduct.getId());

        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(UUID id) {
        return false;
    }

    @Override
    public List<Product> filterByCategoryAndStore(String categoryName, Store store) {
        return null;
    }

    @Override
    public List<Product> filterByCategory(String categoryName) {
        return null;
    }

    @Override
    public void validateProduct(Product product, Product existingProduct) {
        // Store validation
        if (product.getStore() == null || !storeService.existsById(product.getStore().getId())) {
            throw new ResourceNotFoundException("Store");
        }

        // Category validation
        if (product.getCategory() == null || !this.categoryService.existsById(product.getCategory().getId())) {
            throw new ResourceNotFoundException("Category");
        }

        // Price validation
        if (product.getPrice() != null && product.getPrice() < 0) {
            throw new NotValidConstraintException("Price cannot be negative!");
        }

        // Quantity validation
        if (product.getQuantity() != null && product.getQuantity() < 0) {
            throw new NotValidConstraintException("Quantity cannot be negative!");
        }

        // Creation case
        if (existingProduct == null) {
            if (productRepository.existsByNameAndStoreId(product.getName(), product.getStore().getId())) {
                throw new NotValidConstraintException("Product with this name already exists in the store");
            }
        }
        // Update case
        else {
            if (!existingProduct.getName().equals(product.getName()) &&
                    productRepository.existsByNameAndStoreId(product.getName(), product.getStore().getId())) {
                throw new NotValidConstraintException("Cannot update to this name as it already exists in the store");
            }
        }
    }

    @Override
    public Product findBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
    }

    @Override
    public List<Product> findByAuthUserStore() {
        Store store = this.authService.getAuthenticatedUser().getStore();
        return productRepository.findByStore(store);
    }
}
