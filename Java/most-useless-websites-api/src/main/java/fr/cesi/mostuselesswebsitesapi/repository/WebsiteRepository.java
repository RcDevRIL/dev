package fr.cesi.mostuselesswebsitesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.cesi.mostuselesswebsitesapi.entity.Website;

public interface WebsiteRepository extends JpaRepository<Website, String>{

	List<Website> findByCategoryId(Long categoryId);

	@Query("SELECT w FROM Website w WHERE w.url LIKE %:search%")
	List<Website> findAllThatContains(String search); 

}
