package com.youcode.sudest_market.domain;

import com.youcode.sudest_market.domain.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "total_amount")
    private Double totalAmount;
    private OrderStatus status;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "to_address")
    private String toAddress;
    
    // Relationships ************************************************
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    @OneToOne(mappedBy = "order")
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // **************************************************************
    public Order() {
    }

    public Order(Double totalAmount, OrderStatus status, String fromAddress, String toAddress) {
        this.totalAmount = totalAmount;
        this.status = status;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getTotalAmount(), order.getTotalAmount()) && getStatus() == order.getStatus() && Objects.equals(getFromAddress(), order.getFromAddress()) && Objects.equals(getToAddress(), order.getToAddress()) && Objects.equals(getAppUser(), order.getAppUser()) && Objects.equals(getOrderProducts(), order.getOrderProducts()) && Objects.equals(getPayment(), order.getPayment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTotalAmount(), getStatus(), getFromAddress(), getToAddress(), getAppUser(), getOrderProducts(), getPayment());
    }
}
