package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.domain.City;
import com.youcode.sudest_market.domain.enums.Role;
import com.youcode.sudest_market.dto.auth.LoginRequest;
import com.youcode.sudest_market.dto.auth.RegisterRequest;
import com.youcode.sudest_market.exception.EntityNotFoundException;
import com.youcode.sudest_market.exception.MismatchException;
import com.youcode.sudest_market.repository.AppUserRepository;
import com.youcode.sudest_market.repository.CityRepository;
import com.youcode.sudest_market.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final CityRepository cityRepository;

    public AuthServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, CityRepository cityRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.cityRepository = cityRepository;
    }

    private String generateToken(AppUser appUser) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("sudest_market")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(15, ChronoUnit.DAYS))
                .subject(appUser.getEmail())
                .claim("role", "ROLE_" + appUser.getRole().name())
                .claim("username", appUser.getUsername())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (registerRequest.getConfirmPassword() == null || !registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new MismatchException("confirmPassword", "Passwords do not match");
        }
        var user = new AppUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.CUSTOMER);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAddress(registerRequest.getAddress());
        City city = cityRepository.findByName(registerRequest.getCity())
                .orElseThrow(() -> new EntityNotFoundException("City"));
        user.setCity(city);
        appUserRepository.save(user);

        return generateToken(user);
    }

    @Override
    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = appUserRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        return generateToken(user);
    }
}
