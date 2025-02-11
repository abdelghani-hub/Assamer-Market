package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}