package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}