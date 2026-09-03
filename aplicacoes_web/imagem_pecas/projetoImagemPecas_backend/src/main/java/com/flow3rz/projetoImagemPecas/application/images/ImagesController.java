package com.flow3rz.projetoImagemPecas.application.images;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;
import com.flow3rz.projetoImagemPecas.domain.enums.ImageExtension;
import com.flow3rz.projetoImagemPecas.domain.services.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {
    private static final Logger log = LoggerFactory.getLogger(ImagesController.class);

    private final ImageService service;

    @PostMapping("/")
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
            ) throws IOException {

        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        log.info("Nome definido: {}", name);
        log.info("Tags recebidas: {}", tags);

        Image image = Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
        service.save(image);

        return ResponseEntity.ok().build();
    }
}
