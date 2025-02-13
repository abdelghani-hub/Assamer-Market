package com.youcode.sudest_market.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Relationships ************************************************
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    // **************************************************************

    public Favorite() {
    }

    public Favorite(Product product, AppUser appUser) {
        this.product = product;
        this.appUser = appUser;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorite favorite)) return false;
        return Objects.equals(getId(), favorite.getId()) && Objects.equals(getProduct(), favorite.getProduct()) && Objects.equals(getAppUser(), favorite.getAppUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getAppUser());
    }
}
