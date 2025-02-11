package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
}