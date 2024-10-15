package com.example.coursesystem.service.impl;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.UUID;

@Service
public class AzureBlobService {

    @Value("${connection.endpoint}")
    private String connectionString;

    @Value("${container.name}")
    private String containerName;

    public String uploadVideoToAzure(MultipartFile file) throws IOException {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        String uniqueFileName = generateUniqueFileName(file, "lesson");

        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(tempFile);

        BlobClient blobClient = containerClient.getBlobClient(uniqueFileName);
        blobClient.uploadFromFile(tempFile.getAbsolutePath(), true);

        String videoUrl = blobClient.getBlobUrl();

        tempFile.delete();

        return videoUrl;
    }

    private String generateUniqueFileName(MultipartFile file, String lessonTitle) {
        String normalizedTitle = lessonTitle.replaceAll("\\s+", "_");

        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

        String uuid = UUID.randomUUID().toString();

        return normalizedTitle + "_" + uuid + fileExtension;
    }
}
