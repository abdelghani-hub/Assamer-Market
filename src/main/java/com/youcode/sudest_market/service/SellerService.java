package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.SellerRequest;
import com.youcode.sudest_market.domain.enums.RequestStatus;

import java.util.UUID;

public interface SellerService {

    SellerRequest createSellerRequest();

    boolean updateSellerRequest(UUID requestId, RequestStatus status);
}
