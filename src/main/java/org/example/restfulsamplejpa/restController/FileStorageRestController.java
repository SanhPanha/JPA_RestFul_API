package org.example.restfulsamplejpa.restController;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.restfulsamplejpa.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileStorageRestController {
    private final FileStorageService fileStorageService;

//    private String generateImageUrl (String fileName, HttpServletRequest request) {
//        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + fileName;
//    }

    private String generateImageUrl(String fileName, HttpServletRequest request) {
        return String.format("%s://%s:%sd/images/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                fileName
        );
    }


    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public HashMap<String, String> uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) {

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Successfully uploaded file");
        response.put("payload", generateImageUrl(fileStorageService.uploadSingleFile(file), request));
        response.put("status", HttpStatus.CREATED.value() + "");
        return response;
    }

    @PostMapping(value = "/multiple", consumes = {"multipart/form-data"})
    public HashMap<String, Object> uploadMultipleFile(@RequestPart("files") MultipartFile[] files) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Successfully uploaded files");
        response.put("payload", fileStorageService.uploadMultipleFiles(files));
        response.put("status", HttpStatus.CREATED.value()+ "");
        return response;
    }

    @DeleteMapping("/{fileName}")
    public HashMap<String, String> deleteFile(@PathVariable String fileName){
        fileStorageService.deleteFileByName(fileName);
        return null;
    }
}
