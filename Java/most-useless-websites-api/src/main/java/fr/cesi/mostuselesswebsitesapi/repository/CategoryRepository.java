package fr.cesi.mostuselesswebsitesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cesi.mostuselesswebsitesapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
