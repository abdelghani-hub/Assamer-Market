package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.Store;

import java.util.UUID;

public interface StoreService {

    Store createStore(Store store);
    Store updateStore(Store store);

    boolean existsById(UUID id);
}
