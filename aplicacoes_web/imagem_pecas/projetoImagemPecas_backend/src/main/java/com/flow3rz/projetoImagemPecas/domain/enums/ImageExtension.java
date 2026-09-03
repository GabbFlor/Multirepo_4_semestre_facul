package com.flow3rz.projetoImagemPecas.domain.enums;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtension {
    PNG(MediaType.IMAGE_PNG),
    JPG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF);

    private final MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension valueOf(MediaType mediaType) {
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }
}
