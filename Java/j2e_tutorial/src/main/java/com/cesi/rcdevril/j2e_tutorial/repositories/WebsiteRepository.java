package com.cesi.rcdevril.j2e_tutorial.repositories;

import com.cesi.rcdevril.j2e_tutorial.entities.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteRepository extends JpaRepository<Website, Long> {

    List<Website> findByCategoryId(Long id);
}