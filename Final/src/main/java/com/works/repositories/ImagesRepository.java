package com.works.repositories;

import com.works.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagesRepository extends JpaRepository<Images, Long> {
    Optional<Images> findByPathEquals(String path);

}