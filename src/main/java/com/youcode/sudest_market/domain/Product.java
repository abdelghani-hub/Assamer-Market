package com.youcode.sudest_market.domain;

import com.youcode.sudest_market.domain.enums.ProductStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String slug;
    private String summary;

    @Column(length = 1000)
    private String description;

    private Integer quantity;
    private Double price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "primary_image_src")
    private String primaryImageSrc;

    // Relationships ************************************************
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;
    
    @OneToMany(mappedBy = "product")
    private List<Attachment> attachments;

    // **************************************************************

    public Product() {
    }

    public Product(String name, String slug, String summary, String description, Integer quantity, Double price, ProductStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, String primaryImageSrc) {
        this.name = name;
        this.slug = slug;
        this.summary = summary;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.primaryImageSrc = primaryImageSrc;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
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

    public String getPrimaryImageSrc() {
        return primaryImageSrc;
    }

    public void setPrimaryImageSrc(String primaryImageSrc) {
        this.primaryImageSrc = primaryImageSrc;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
    
    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }
    
    public void removeFavorite(Favorite favorite) {
        this.favorites.remove(favorite);
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
    
    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }
    
    public void removeOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.remove(orderProduct);
    }

    public List<Attachment> getPhotos() {
        return attachments;
    }

    public void setPhotos(List<Attachment> attachments) {
        this.attachments = attachments;
    }
    
    public void addPhoto(Attachment attachment) {
        this.attachments.add(attachment);
    }
    
    public void removePhoto(Attachment attachment) {
        this.attachments.remove(attachment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId()) || Objects.equals(getName(), product.getName()) || Objects.equals(getSlug(), product.getSlug()) || Objects.equals(getSummary(), product.getSummary()) || Objects.equals(getDescription(), product.getDescription()) || Objects.equals(getQuantity(), product.getQuantity()) || Objects.equals(getPrice(), product.getPrice()) || getStatus() == product.getStatus() || Objects.equals(getCreatedAt(), product.getCreatedAt()) || Objects.equals(getUpdatedAt(), product.getUpdatedAt()) || Objects.equals(getPrimaryImageSrc(), product.getPrimaryImageSrc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSlug(), getSummary(), getDescription(), getQuantity(), getPrice(), getStatus(), getCreatedAt(), getUpdatedAt(), getPrimaryImageSrc());
    }
}
