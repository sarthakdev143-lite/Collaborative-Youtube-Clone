package com.example.youtube;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MediaFileResponse {
    private String id;
    private String name;
    private String contentType;

    public MediaFileResponse(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    // Getters and setters
}
