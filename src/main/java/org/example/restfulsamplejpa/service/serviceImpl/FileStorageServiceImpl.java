package org.example.restfulsamplejpa.service.serviceImpl;

import org.example.restfulsamplejpa.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${file_storage.image_location}")
    String fileStorageLocation;

    @Override
    public String uploadSingleFile(MultipartFile file) {
        try {
            Path imageStoragePath = Path.of(fileStorageLocation);
            if (!Files.exists(imageStoragePath)) {
                Files.createDirectories(imageStoragePath);
            }

//            destinationPath => filestorage/image/filename.jpg
            String newFileName = UUID.randomUUID() + "." + file.getOriginalFilename().split("\\.")[1];
            Path imageFullPath = imageStoragePath.resolve(newFileName);

            Files.copy(file.getInputStream(), imageFullPath, StandardCopyOption.REPLACE_EXISTING);

            return newFileName;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while creating directory");
        }
        return null;
    }

    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] files) {
        List<String> fileName = new ArrayList<>();
        for(var file: files){
            fileName.add(uploadSingleFile(file));
        }
        return fileName;
    }
}
