package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.domain.SellerRequest;
import com.youcode.sudest_market.domain.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerRequestRepository extends JpaRepository<SellerRequest, UUID> {
    boolean existsById(UUID id);

    boolean existsByAppUserAndStatus(AppUser appUser, RequestStatus requestStatus);
}