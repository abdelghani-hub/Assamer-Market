package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {

    Optional<City> findByName(String name);
}
