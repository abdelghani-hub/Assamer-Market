package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.domain.enums.Role;
import com.youcode.sudest_market.exception.EntityNotFoundException;
import com.youcode.sudest_market.exception.InvalidCredentialsException;
import com.youcode.sudest_market.exception.NullOrBlankArgException;
import com.youcode.sudest_market.service.AppUserService;
import com.youcode.sudest_market.exception.AlreadyExistException;
import com.youcode.sudest_market.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public AppUser save(@Valid AppUser appUser) {
        // Check if the appUser already exists : username, email, cin
        if (this.findByUsername(appUser.getUsername()).isPresent()) {
            throw new AlreadyExistException("username", appUser.getUsername());
        }
        if (this.findByEmail(appUser.getEmail()).isPresent()) {
            throw new AlreadyExistException("email", appUser.getEmail());
        }
        // Save & Map the appUser
        return appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        if(username == null || username.isEmpty()) {
            return Optional.empty();
        }
        return appUserRepository.findByUsername(username);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        if(email == null || email.isEmpty()) {
            return Optional.empty();
        }
        return appUserRepository.findByEmail(email);
    }

    public Optional<AppUser> login(String login, String password) throws InvalidCredentialsException {
        if (login == null || login.isEmpty()) {
            return Optional.empty();
        }
        Optional<AppUser> userOp = appUserRepository.findByUsernameOrEmail(login, login);
        if (userOp.isEmpty() || !passwordEncoder.matches(password, userOp.get().getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return userOp;
    }

    @Override
    public AppUser findById(UUID id) {
        Optional<AppUser> userOP = appUserRepository.findById(id);
        if (userOP.isEmpty()) {
            throw new EntityNotFoundException("AppUser");
        }
        return userOP.get();
    }

    public AppUser update(AppUser appUser) {
        // Check if the appUser already exists id
        AppUser originalAppUser = this.findById(appUser.getId());

        // Check if the appUser already exists : username, email, cin
        if (appUserRepository.existsByUsernameAndIdNot(appUser.getUsername(), originalAppUser.getId())) {
            throw new AlreadyExistException("username", appUser.getUsername());
        }
        if (appUserRepository.existsByEmailAndIdNot(appUser.getEmail(), originalAppUser.getId())) {
            throw new AlreadyExistException("email", appUser.getEmail());
        }

        // update
        appUser.setRole(originalAppUser.getRole());
        return appUserRepository.save(appUser);
    }

    public boolean delete(UUID id) {
        if (appUserRepository.existsById(id)) {
            // delete
            appUserRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<AppUser> findByUsernameOrEmail(String usernameORemail) {
        if(usernameORemail == null || usernameORemail.isEmpty()) {
            throw new NullOrBlankArgException("username or email");
        }
        return appUserRepository.findByUsernameContainingOrEmailContaining(usernameORemail, usernameORemail);
    }

    @Override
    public AppUser findByUsernameOrEmail(String username, String email) {
        if(username == null || email.isEmpty()) {
            throw new NullOrBlankArgException("username or email");
        }
        return appUserRepository
                .findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new EntityNotFoundException("User"));
    }

    @Override
    public Page<AppUser> findAll(Pageable pageable) {
        return appUserRepository.findAll(pageable);
    }

    @Override
    public AppUser updateByUsername(String username, AppUser appUser) {
        AppUser originalAppUser = this.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("AppUser"));
        if (appUserRepository.existsByEmailAndUsernameNot(appUser.getEmail(), appUser.getUsername())) {
            throw new AlreadyExistException("email", appUser.getEmail());
        }

        originalAppUser.setFirstName(appUser.getFirstName());
        originalAppUser.setLastName(appUser.getLastName());
        originalAppUser.setUsername(appUser.getUsername());
        originalAppUser.setEmail(appUser.getEmail());
        originalAppUser.setRole(appUser.getRole());

        return appUserRepository.save(originalAppUser);
    }

    @Override
    public AppUser updateRole(AppUser appUser, Role role) {
        appUser.setRole(role);
        return appUserRepository.save(appUser);
    }
}