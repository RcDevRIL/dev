package com.cesi.rcdevril.j2e_tutorial.repositories;

import com.cesi.rcdevril.j2e_tutorial.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}