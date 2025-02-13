package com.youcode.sudest_market.web.api.v1.controller;

import com.youcode.sudest_market.domain.enums.RequestStatus;
import com.youcode.sudest_market.service.SellerService;
import com.youcode.sudest_market.web.api.v1.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> createSellerRequest() {

        sellerService.createSellerRequest();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(null, "Requested successfully, You'll get a response as soon as possible.", null));
    }

    @PutMapping("/request/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> acceptSellerRequest(@RequestParam String requestId) {

        if (sellerService.updateSellerRequest(UUID.fromString(requestId), RequestStatus.ACCEPTED)){
            return ResponseEntity.ok(ApiResponse.success(null, "Request accepted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Request not found", null));
    }

    @PutMapping("/request/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> rejectSellerRequest(@RequestParam String requestId) {

        if (sellerService.updateSellerRequest(UUID.fromString(requestId), RequestStatus.REJECTED)) {
            return ResponseEntity.ok(ApiResponse.success(null, "Request rejected successfully", null));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Request not found", null));
    }
}
