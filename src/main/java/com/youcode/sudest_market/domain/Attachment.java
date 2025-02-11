package com.youcode.sudest_market.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String src;
    private String fileType;
    private String comment;

    // Polymorphic relationship
    private UUID entityId;
    private String entityType;

    public Attachment(String src, String fileType, String comment, UUID entityId, String entityType) {
        this.src = src;
        this.fileType = fileType;
        this.comment = comment;
        this.entityId = entityId;
        this.entityType = entityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attachment attachment)) return false;
        return Objects.equals(getId(), attachment.getId()) &&
                Objects.equals(getSrc(), attachment.getSrc()) &&
                Objects.equals(getEntityId(), attachment.getEntityId()) &&
                Objects.equals(getEntityType(), attachment.getEntityType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSrc(), getEntityId(), getEntityType());
    }
}
