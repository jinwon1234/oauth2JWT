package com.oauth2.jwt.fileupload.controller;

import com.oauth2.jwt.fileupload.dto.UploadFileDto;
import com.oauth2.jwt.fileupload.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/files")
    public ResponseEntity<UploadFileDto> uploadFile(@RequestParam String fileName) {
        UploadFileDto presignedURL = uploadService.getPresignedURL(fileName);

        log.info("Presigned URL: {}", presignedURL.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(presignedURL);
    }
}
