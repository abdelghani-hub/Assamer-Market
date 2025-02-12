package com.youcode.sudest_market.web.api.v1.controller;

import com.youcode.sudest_market.service.SellerService;
import com.youcode.sudest_market.web.api.v1.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
