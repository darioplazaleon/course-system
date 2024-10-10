package com.example.coursesystem.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

public class AzureConfig {

    BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
            .endpoint("https://coursesystem.blob.core.windows.net/")
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
}