package com.youcode.sudest_market.web.api.v1.controller;

import com.youcode.sudest_market.domain.Product;
import com.youcode.sudest_market.service.CategoryService;
import com.youcode.sudest_market.service.ProductService;
import com.youcode.sudest_market.web.api.v1.response.ApiResponse;
import com.youcode.sudest_market.web.vm.mapper.ProductVmMapper;
import com.youcode.sudest_market.web.vm.product.ProductVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductVmMapper productVmMapper;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductVM productVM) {
        Product product = productVmMapper.toProduct(productVM);
        product.setCategory(categoryService.findByNameIgnoreCase(productVM.getCategoryName()));
        productService.createProduct(product);
        ProductVM savedProductVM = productVmMapper.toVM(product);
        savedProductVM.setCategoryName(product.getCategory().getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(savedProductVM, "Product created successfully", new String[]{"/products/" + product.getSlug()}));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable String slug) {
        Product product = productService.findBySlug(slug);
        ProductVM productVM = productVmMapper.toVM(product);
        productVM.setCategoryName(product.getCategory().getName());
        return ResponseEntity.ok(ApiResponse.success(productVM, "Product retrieved successfully", null));
    }

    @PutMapping("/{slug}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String slug, @Valid @RequestBody ProductVM productVM) {
        productVM.setSlug(slug);
        Product product = productVmMapper.toProduct(productVM);
        product.setCategory(categoryService.findByNameIgnoreCase(productVM.getCategoryName()));
        productService.updateProduct(product);
        return ResponseEntity.ok(ApiResponse.success(productVM, "Product updated successfully", new String[]{"/products/" + product.getSlug()}));
    }
}
