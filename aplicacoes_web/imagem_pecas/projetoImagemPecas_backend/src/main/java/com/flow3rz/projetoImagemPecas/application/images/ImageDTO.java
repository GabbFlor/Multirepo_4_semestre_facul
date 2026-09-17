package com.flow3rz.projetoImagemPecas.application.images;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ImageDTO {
    private String url;
    private String name;
    private String extension;
    private String size;
    private LocalDateTime uploadDate;
}
