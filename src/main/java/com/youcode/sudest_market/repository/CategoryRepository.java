package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByName(String name);
    Optional<Category> findByNameAndIdNot(String name, UUID nameId);
}
