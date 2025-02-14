package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.Store;
import com.youcode.sudest_market.repository.StoreRepository;
import com.youcode.sudest_market.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        store.setCreatedAt(LocalDateTime.now());
        store.setUpdatedAt(LocalDateTime.now());
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Store store) {
        store.setUpdatedAt(LocalDateTime.now());
        return storeRepository.save(store);
    }

    @Override
    public boolean existsById(UUID id) {
        return storeRepository.existsById(id);
    }
}
