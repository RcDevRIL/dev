package fr.cesi.mostuselesswebsitesapi.service;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.mostuselesswebsitesapi.entity.Website;
import fr.cesi.mostuselesswebsitesapi.repository.WebsiteRepository;

@Service
public class WebsiteServiceImpl implements WebsiteService {
	
	@Autowired
	private  WebsiteRepository repository;

	@Override
	public Website findByCategory(Long categoryId) {
		List<Website> websites = repository.findByCategoryId(categoryId);
	
		return websites.get(new Random().nextInt(websites.size()));
	}

	@Override
	public Website save(Website website) {
		return repository.save(website);
	}

	@Override
	public List<Website> findAllThatContains(String search) {
		return repository.findAllThatContains(search);
	}
	

}
