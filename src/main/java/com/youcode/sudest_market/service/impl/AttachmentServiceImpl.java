package com.youcode.sudest_market.service.impl;

import com.youcode.sudest_market.domain.Attachment;
import com.youcode.sudest_market.exception.ResourceNotFoundException;
import com.youcode.sudest_market.repository.AttachmentRepository;
import com.youcode.sudest_market.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public Attachment saveAttachment(MultipartFile file, String entityType, UUID entityId, String comment) throws IOException {
        // Create directory if not exists
        Path directory = Paths.get(UPLOAD_DIR + entityType);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        // Generate unique file name
        String fileName = entityId + "_" + file.getOriginalFilename();
        Path filePath = directory.resolve(fileName);

        // Save file to disk
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save metadata in DB
        Attachment attachment = new Attachment(
                filePath.toString(),
                file.getContentType(),
                comment,
                entityId,
                entityType
        );
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment getAttachment(UUID id) {
        return attachmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Attachment")
        );
    }

    @Override
    public byte[] getFileBytes(Attachment attachment) throws IOException {
        return Files.readAllBytes(Paths.get(attachment.getSrc()));
    }

    @Override
    public List<Attachment> findByEntityTypeAndEntityId(String entityType, UUID entityId) {
        return attachmentRepository.findByEntityTypeAndEntityId(entityType,
                entityId);
    }
}
