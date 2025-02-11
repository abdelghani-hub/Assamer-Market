package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.Category;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    @Transactional
    Category save(@Valid Category category);

    Category findById(UUID id);

    Category update(Category category, UUID id);

    boolean delete(UUID id);

    Page<Category> findAll(Pageable pageable);

    Category findByName(String name);
}
