package kicc.cicd.gcp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;

@Service
public class GCPStorageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;
    @Value("${spring.cloud.gcp.storage.credentials.location}")
    private Resource keyFileResource;

    private Storage storage;

    @PostConstruct
    public void init() throws IOException {
        this.storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFileResource.getInputStream()))
                .build()
                .getService();
    }

    public String uploadFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        try {
            String uuid = UUID.randomUUID().toString();
            String contentType = multipartFile.getContentType();

            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                    .setContentType(contentType)
                    .build();

            storage.create(blobInfo, multipartFile.getInputStream());

            return "https://storage.googleapis.com/" + bucketName + "/" + uuid;

        } catch (IOException e) {
            throw new RuntimeException("file upload failed", e);
        }
    }
}