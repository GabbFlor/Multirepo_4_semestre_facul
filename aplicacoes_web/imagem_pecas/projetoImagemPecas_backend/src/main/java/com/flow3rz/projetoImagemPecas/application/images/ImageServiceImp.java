package com.flow3rz.projetoImagemPecas.application.images;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;
import com.flow3rz.projetoImagemPecas.domain.services.ImageService;
import com.flow3rz.projetoImagemPecas.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

    // essa é a forma teoricamente correta de fazer uma injeção de dependência, não da forma antiga com o @autowired
    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }
}
