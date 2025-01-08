package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.AppUser;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AppUserService {
    AppUser save(@Valid AppUser appUser);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    AppUser findById(UUID id);

    AppUser findByUsernameOrEmail(String username, String email);

    Page<AppUser> findAll(Pageable pageable);

    AppUser updateByUsername(String username, AppUser appUser);
}
