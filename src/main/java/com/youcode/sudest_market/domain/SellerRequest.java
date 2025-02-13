package com.youcode.sudest_market.domain;

import com.youcode.sudest_market.domain.enums.RequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class SellerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private RequestStatus status;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    // Relationships ************************************************
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    
    // **************************************************************

    public SellerRequest() {
    }

    public SellerRequest(RequestStatus status, LocalDateTime requestedAt) {
        this.status = status;
        this.requestedAt = requestedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
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
        if (!(o instanceof SellerRequest that)) return false;
        return Objects.equals(getId(), that.getId()) && getStatus() == that.getStatus() && Objects.equals(getRequestedAt(), that.getRequestedAt()) && Objects.equals(getAppUser(), that.getAppUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getRequestedAt(), getAppUser());
    }
}
