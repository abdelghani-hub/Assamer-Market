package com.youcode.sudest_market.domain;

import com.youcode.sudest_market.domain.enums.StoreStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relationships ************************************************
    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser owner;

    @OneToMany(
            mappedBy = "store"
    )
    private List<Product> products;
    
    // **************************************************************

    public Store() {
    }

    public Store(StoreStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StoreStatus getStatus() {
        return status;
    }

    public void setStatus(StoreStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store store)) return false;
        return Objects.equals(getId(), store.getId()) && getStatus() == store.getStatus() && Objects.equals(getCreatedAt(), store.getCreatedAt()) && Objects.equals(getUpdatedAt(), store.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getCreatedAt(), getUpdatedAt());
    }
}
