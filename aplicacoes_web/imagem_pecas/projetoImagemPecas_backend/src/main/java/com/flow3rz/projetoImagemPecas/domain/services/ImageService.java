package com.flow3rz.projetoImagemPecas.domain.services;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;
import com.flow3rz.projetoImagemPecas.domain.enums.ImageExtension;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> getById(String id);

    List<Image> search (ImageExtension extension, String query);
}
