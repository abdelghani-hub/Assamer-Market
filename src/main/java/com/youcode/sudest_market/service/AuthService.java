package com.youcode.sudest_market.service;

import com.youcode.sudest_market.dto.auth.LoginRequest;
import com.youcode.sudest_market.dto.auth.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
