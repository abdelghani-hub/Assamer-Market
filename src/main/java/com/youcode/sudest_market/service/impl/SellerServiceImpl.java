package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.domain.SellerRequest;
import com.youcode.sudest_market.domain.enums.RequestStatus;
import com.youcode.sudest_market.exception.NotValidConstraintException;
import com.youcode.sudest_market.service.AuthService;
import com.youcode.sudest_market.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.youcode.sudest_market.repository.SellerRequestRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRequestRepository sellerRequestRepository;
    private final AuthService authService;

    @Override
    public SellerRequest createSellerRequest() {
        // Get authenticated user
        AppUser appUser = authService.getAuthenticatedUser();

        // No already existing request pending
        if (sellerRequestRepository.existsByAppUserAndStatus(appUser, RequestStatus.PENDING)) {
            throw new NotValidConstraintException("You already have a pending request, please wait for the response.");
        }

        // Create and save the SellerRequest
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.setAppUser(appUser);
        sellerRequest.setStatus(RequestStatus.PENDING);
        sellerRequest.setRequestedAt(LocalDateTime.now());

        return sellerRequestRepository.save(sellerRequest);
    }

    @Override
    public boolean updateSellerRequest(UUID requestId, RequestStatus status) {
        // Get the request
        SellerRequest sellerRequest = sellerRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotValidConstraintException("Request not found"));

        if (sellerRequest.getStatus() == RequestStatus.ACCEPTED && status == RequestStatus.REJECTED) {
            throw new NotValidConstraintException("You can't reject an accepted request.");
        }
        // Update the request status
        sellerRequest.setStatus(status);
        sellerRequestRepository.save(sellerRequest);

        return true;
    }
}
