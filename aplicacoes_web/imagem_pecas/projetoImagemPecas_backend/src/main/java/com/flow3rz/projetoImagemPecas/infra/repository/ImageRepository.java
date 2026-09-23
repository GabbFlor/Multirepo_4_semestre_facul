package com.flow3rz.projetoImagemPecas.infra.repository;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;
import com.flow3rz.projetoImagemPecas.domain.enums.ImageExtension;
import com.flow3rz.projetoImagemPecas.infra.repository.specs.GenericSpecs;
import com.flow3rz.projetoImagemPecas.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

import static com.flow3rz.projetoImagemPecas.infra.repository.specs.ImageSpecs.nameLike;
import static com.flow3rz.projetoImagemPecas.infra.repository.specs.ImageSpecs.tagsLike;
import static org.springframework.data.jpa.domain.Specification.anyOf;
import static org.springframework.data.jpa.domain.Specification.where;

//@Repository
public interface ImageRepository extends JpaRepository<Image, String>,
        JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query ) {
        Specification<Image> spec = where(GenericSpecs.conjuction());

        if (extension != null) {
            spec = spec.and(ImageSpecs.extensionEqual(extension));
        }

        if (StringUtils.hasText(query)) {
            spec = spec.and(anyOf(nameLike(query), tagsLike(query)));
        }

        return findAll(spec);
    }
}
