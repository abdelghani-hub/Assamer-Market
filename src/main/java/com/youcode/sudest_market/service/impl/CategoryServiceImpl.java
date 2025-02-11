package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.Category;
import com.youcode.sudest_market.exception.AlreadyExistException;
import com.youcode.sudest_market.exception.EntityNotFoundException;
import com.youcode.sudest_market.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements com.youcode.sudest_market.service.CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category save(@Valid Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new AlreadyExistException("Name", category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category"));
    }

    @Override
    public Category update(Category category, UUID id) {
        if (categoryRepository.findByNameAndIdNot(category.getName(), id).isPresent()) {
            throw new AlreadyExistException("Name", category.getName());
        }
        Category categoryToUpdate = this.findById(id);
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public boolean delete(UUID id) {
        if (categoryRepository.existsById(id)) {
            // delete
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<Category> page(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category"));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}