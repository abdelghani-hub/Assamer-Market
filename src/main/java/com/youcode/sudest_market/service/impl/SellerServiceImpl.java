package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.domain.SellerRequest;
import com.youcode.sudest_market.domain.Store;
import com.youcode.sudest_market.domain.enums.RequestStatus;
import com.youcode.sudest_market.domain.enums.Role;
import com.youcode.sudest_market.domain.enums.StoreStatus;
import com.youcode.sudest_market.exception.NotValidConstraintException;
import com.youcode.sudest_market.service.*;
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
    private final MailService mailService;
    private final AppUserService userService;
    private final StoreService storeService;

    @Override
    public SellerRequest createSellerRequest() {
        // Get authenticated user
        AppUser appUser = authService.getAuthenticatedUser();

        // No already existing request pending
        if (sellerRequestRepository.existsByAppUserAndStatus(appUser, RequestStatus.PENDING)) {
            throw new NotValidConstraintException("You already have a pending request, please wait for a response.");
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

        //  Cant reject accepted request
        if (status == RequestStatus.REJECTED && sellerRequest.getStatus() == RequestStatus.ACCEPTED) {
            throw new NotValidConstraintException("You can't reject an accepted request.");
        }

        // Update the request status
        sellerRequest.setStatus(status);
        sellerRequestRepository.save(sellerRequest);

        // Handle accept case
        if (status == RequestStatus.ACCEPTED) {
            // Update the user role
            AppUser requester = sellerRequest.getAppUser();
            this.userService.updateRole(requester, Role.SELLER);

            // create seller store
            Store store = new Store();
            store.setOwner(requester);
            store.setStatus(StoreStatus.NEW);
            this.storeService.createStore(store);

            // Send email
            this.mailService.sellerRequestAccepted(requester);
        }

        return true;
    }
}
