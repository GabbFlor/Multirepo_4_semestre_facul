package com.flow3rz.projetoImagemPecas.domain.services;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;

import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> getById(String id);
}
