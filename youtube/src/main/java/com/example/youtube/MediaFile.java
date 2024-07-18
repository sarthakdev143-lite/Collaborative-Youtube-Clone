package com.example.youtube;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mediaFiles")
@Data
public class MediaFile {
    @Id
    private String id;
    private String name;
    private String contentType;
    private byte[] content;
}
