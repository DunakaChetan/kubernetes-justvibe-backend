package JustVibe.com.service;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AzureBlobService {

    private final BlobContainerClient containerClient;

    public AzureBlobService(
            @Value("${azure.storage.account-name}") String accountName,
            @Value("${azure.storage.account-key}") String accountKey,
            @Value("${azure.storage.container-name}") String containerName,
            @Value("${azure.storage.endpoint}") String endpoint) {

        String connectionString = String.format(
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                accountName, accountKey
        );

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        this.containerClient = serviceClient.getBlobContainerClient(containerName);

        if (!this.containerClient.exists()) {
            this.containerClient.create();
        }
    }

    public String uploadFile(MultipartFile file) throws IOException {
        BlobClient blobClient = containerClient.getBlobClient(file.getOriginalFilename());
        // Upload (overwrite = true)
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        // Set content type (fallback to audio/mpeg for mp3)
        String contentType = file.getContentType() != null ? file.getContentType() : "audio/mpeg";
        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(contentType);
        blobClient.setHttpHeaders(headers);

        return blobClient.getBlobUrl();
    }

    public void deleteFile(String filename) {
        BlobClient blobClient = containerClient.getBlobClient(filename);
        blobClient.delete();
    }
    
}
