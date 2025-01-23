package com.youcode.sudest_market.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String src;

    // Relationships ************************************************
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "product_id")
    private Product product;
    
    // **************************************************************

    public Attachment() {
    }

    public Attachment(String src) {
        this.src = src;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attachment attachment)) return false;
        return Objects.equals(getId(), attachment.getId()) && Objects.equals(getSrc(), attachment.getSrc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSrc());
    }
}
