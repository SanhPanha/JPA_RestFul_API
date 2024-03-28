package org.example.restfulsamplejpa.restController;


import lombok.RequiredArgsConstructor;
import org.example.restfulsamplejpa.service.FileStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileStorageRestController {
    private final FileStorageService fileStorageService;


    @PostMapping(value = "", consumes = "multipart/form-data")
    public HashMap<String, String> uploadFile(@RequestPart("file") MultipartFile file) {

        HashMap<String, String> response = new HashMap<>();
        response.put("payload", fileStorageService.uploadSingleFile(file));
        return response;
    }
}
