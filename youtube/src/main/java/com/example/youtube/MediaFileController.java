package com.example.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
public class MediaFileController {

    @Autowired
    private MediaFileService mediaFileService;

    @PostMapping("/upload")
    public ResponseEntity<MediaFile> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            MediaFile mediaFile = mediaFileService.storeFile(file);
            return new ResponseEntity<>(mediaFile, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        return mediaFileService.getFile(id)
                .map(mediaFile -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(mediaFile.getContentType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mediaFile.getName() + "\"")
                        .body(mediaFile.getContent()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/files")
    public ResponseEntity<List<MediaFileResponse>> getAllFiles() {
        List<MediaFile> files = mediaFileService.getAllFiles();
        List<MediaFileResponse> fileResponses = files.stream()
                .map(file -> new MediaFileResponse(file.getId(), file.getName(), file.getContentType()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(fileResponses, HttpStatus.OK);
    }
}
