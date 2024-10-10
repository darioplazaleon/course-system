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

        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(tempFile);

        BlobClient blobClient = containerClient.getBlobClient(file.getOriginalFilename());

        blobClient.uploadFromFile(tempFile.getAbsolutePath(), true);

        String videoUrl = blobClient.getBlobUrl();

        tempFile.delete();

        return videoUrl;
    }
}
