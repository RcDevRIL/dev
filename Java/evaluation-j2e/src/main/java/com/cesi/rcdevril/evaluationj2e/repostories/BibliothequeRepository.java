package com.cesi.rcdevril.evaluationj2e.repostories;

import com.cesi.rcdevril.evaluationj2e.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
}
