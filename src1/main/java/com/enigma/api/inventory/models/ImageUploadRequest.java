package com.enigma.api.inventory.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ImageUploadRequest {
    private MultipartFile file;
}
