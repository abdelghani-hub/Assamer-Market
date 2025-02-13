package com.youcode.sudest_market.domain;

import com.youcode.sudest_market.domain.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String method;
    private Double amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "payed_at")
    private LocalDateTime payedAt;
    
    // Relationships ************************************************
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // **************************************************************

    public Payment() {
    }

    public Payment(String method, Double amount, String currency, PaymentStatus status, LocalDateTime payedAt) {
        this.method = method;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.payedAt = payedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(LocalDateTime payedAt) {
        this.payedAt = payedAt;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(getId(), payment.getId()) && Objects.equals(getMethod(), payment.getMethod()) && Objects.equals(getAmount(), payment.getAmount()) && Objects.equals(getCurrency(), payment.getCurrency()) && getStatus() == payment.getStatus() && Objects.equals(getPayedAt(), payment.getPayedAt()) && Objects.equals(getOrder(), payment.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMethod(), getAmount(), getCurrency(), getStatus(), getPayedAt(), getOrder());
    }
}
