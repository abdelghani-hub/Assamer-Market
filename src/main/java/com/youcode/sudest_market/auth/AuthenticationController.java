package com.youcode.sudest_market.auth;

import com.youcode.sudest_market.dto.auth.LoginRequest;
import com.youcode.sudest_market.dto.auth.RegisterRequest;
import com.youcode.sudest_market.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        Map<String, String> res = new HashMap<>();
        res.put("token", authService.register(request));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody LoginRequest request
    ) {
        Map<String, String> res = new HashMap<>();
        res.put("token", authService.login(request));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}