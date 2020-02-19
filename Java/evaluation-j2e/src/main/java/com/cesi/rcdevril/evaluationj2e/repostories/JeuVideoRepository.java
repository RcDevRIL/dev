package com.cesi.rcdevril.evaluationj2e.repostories;

import com.cesi.rcdevril.evaluationj2e.entities.JeuVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JeuVideoRepository extends JpaRepository<JeuVideo, Long> {
    List<JeuVideo> findByNameContaining(String search);
}
