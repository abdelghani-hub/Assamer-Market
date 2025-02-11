package com.youcode.sudest_market.repository;

import com.youcode.sudest_market.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}