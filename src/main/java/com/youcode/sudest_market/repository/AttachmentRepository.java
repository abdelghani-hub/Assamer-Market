package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    List<Attachment> findByEntityTypeAndEntityId(String entityType, UUID entityId);
}