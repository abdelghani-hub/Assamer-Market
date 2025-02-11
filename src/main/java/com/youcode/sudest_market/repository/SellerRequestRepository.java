package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.SellerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRequestRepository extends JpaRepository<SellerRequest, UUID> {
}