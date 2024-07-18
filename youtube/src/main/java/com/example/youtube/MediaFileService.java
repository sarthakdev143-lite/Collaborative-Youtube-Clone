package com.example.youtube;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaFileService {

    @Autowired
    private MediaFileRepository mediaFileRepository;

    public MediaFile storeFile(MultipartFile file) throws IOException {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setName(file.getOriginalFilename());
        mediaFile.setContentType(file.getContentType());
        mediaFile.setContent(file.getBytes());

        return mediaFileRepository.save(mediaFile);
    }

    public Optional<MediaFile> getFile(String id) {
        return mediaFileRepository.findById(id);
    }

    public List<MediaFile> getAllFiles() {
        return mediaFileRepository.findAll();
    }
}
