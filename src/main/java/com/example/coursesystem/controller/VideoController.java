package com.example.coursesystem.controller;

import com.example.coursesystem.service.impl.AzureBlobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideoController {

    private final AzureBlobService azureBlobService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@RequestParam("file")MultipartFile file) throws IOException {
        String videoUrl = azureBlobService.uploadVideoToAzure(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(videoUrl);
    }
}
