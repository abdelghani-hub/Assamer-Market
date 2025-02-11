package com.youcode.sudest_market.web.api.v1.controller;

import com.youcode.sudest_market.domain.Category;
import com.youcode.sudest_market.service.CategoryService;
import com.youcode.sudest_market.web.api.v1.response.ApiResponse;
import com.youcode.sudest_market.web.vm.category.CategoryVM;
import com.youcode.sudest_market.web.vm.mapper.CategoryVmMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryVmMapper categoryVmMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryVM>> createCategory(@Valid @RequestBody CategoryVM categoryVM) {
        Category category = categoryVmMapper.toEntity(categoryVM);
        Category savedCategory = categoryService.save(category);
        CategoryVM savedCategoryVM = categoryVmMapper.toVM(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(savedCategoryVM, "Category created successfully."));
    }

    @GetMapping("/{name}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponse<CategoryVM>> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.findByName(name);
        CategoryVM categoryVM = categoryVmMapper.toVM(category);
        return ResponseEntity.ok(ApiResponse.success(categoryVM, "Category retrieved successfully."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryVM>> updateCategory(@PathVariable UUID id, @RequestBody CategoryVM categoryVM) {
        Category category = categoryVmMapper.toEntity(categoryVM);
        Category updatedCategory = categoryService.update(category, id);
        CategoryVM updatedCategoryVM = categoryVmMapper.toVM(updatedCategory);
        return ResponseEntity.ok(ApiResponse.success(updatedCategoryVM, "Category updated successfully."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponse<Page<CategoryVM>>> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        Page<CategoryVM> categoryVMs = categories.map(categoryVmMapper::toVM);
        return ResponseEntity.ok(ApiResponse.success(categoryVMs, "Categories retrieved successfully."));
    }
}