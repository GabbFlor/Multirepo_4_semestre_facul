package com.flow3rz.projetoImagemPecas.application.images;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;
import com.flow3rz.projetoImagemPecas.domain.enums.ImageExtension;
import com.flow3rz.projetoImagemPecas.domain.services.ImageService;
import com.flow3rz.projetoImagemPecas.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Image> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return repository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
