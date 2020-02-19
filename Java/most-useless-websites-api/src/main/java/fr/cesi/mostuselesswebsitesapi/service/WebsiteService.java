package fr.cesi.mostuselesswebsitesapi.service;

import java.util.List;

import fr.cesi.mostuselesswebsitesapi.entity.Website;

public interface WebsiteService {

	Website findByCategory(Long categoryId);

	Website save(Website website);
	
	List<Website> findAllThatContains(String search);
	

}
