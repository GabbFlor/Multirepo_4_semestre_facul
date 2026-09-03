package com.flow3rz.projetoImagemPecas.infra.repository;

import com.flow3rz.projetoImagemPecas.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
