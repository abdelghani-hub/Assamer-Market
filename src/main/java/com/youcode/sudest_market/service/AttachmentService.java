package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file, String entityType, UUID entityId, String comment) throws IOException;

    Attachment getAttachment(UUID id);

    byte[] getFileBytes(Attachment attachment) throws IOException;
}
