package org.example.restfulsamplejpa.service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileStorageService {
    String uploadSingleFile(MultipartFile file);
    List<String> uploadMultipleFiles(MultipartFile[] files);

    void deleteFileByName(String fileName);
}
