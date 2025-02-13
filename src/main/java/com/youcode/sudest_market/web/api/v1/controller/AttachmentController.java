package com.youcode.sudest_market.web.api.v1.controller;

import com.youcode.sudest_market.domain.Attachment;
import com.youcode.sudest_market.service.AttachmentService;
import com.youcode.sudest_market.web.api.v1.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    // 📌 Upload File API
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<List<String>>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("entityType") String entityType,
            @RequestParam("entityId") UUID entityId,
            @RequestParam(value = "comment", required = false) String comment) {

        try {
            Attachment attachment = attachmentService.saveAttachment(file, entityType, entityId, comment);
            List<String> links = new ArrayList<>();
            links.add(attachment.getSrc());

            ApiResponse<List<String>> response = ApiResponse.success(null, "File uploaded successfully", links.toArray(new String[0]));
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            ApiResponse<List<String>> response = ApiResponse.error("File upload failed", null);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    // 📌 Download File API
    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable UUID id) {
        Attachment attachment = attachmentService.getAttachment(id);

        try {
            byte[] data = attachmentService.getFileBytes(attachment);
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + attachment.getSrc())
                    .contentType(MediaType.parseMediaType(attachment.getFileType()))
                    .contentLength(data.length)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // 📌 Upload Multiple Files API
    @PostMapping("/upload-multiple")
    public ResponseEntity<ApiResponse<List<String>>> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("entityType") String entityType,
            @RequestParam("entityId") UUID entityId,
            @RequestParam(value = "comment", required = false) String comment) {

        List<String> fileLinks = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Attachment attachment = attachmentService.saveAttachment(file, entityType, entityId, comment);
                fileLinks.add(attachment.getSrc());
            } catch (IOException e) {
                ApiResponse<List<String>> response = ApiResponse.error("File upload failed for one or more files.", null);
                return ResponseEntity.internalServerError().body(response);
            }
        }

        ApiResponse<List<String>> response = ApiResponse.success(null, "Multiple files uploaded", fileLinks.toArray(new String[0]));
        return ResponseEntity.ok(response);
    }
}
