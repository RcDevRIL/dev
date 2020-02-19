package fr.cesi.mostuselesswebsitesapi.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cesi.mostuselesswebsitesapi.entity.Category;
import fr.cesi.mostuselesswebsitesapi.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	

	@Override
	public List<Category> findAll() {
		return this.repository.findAll();
	}


	@Override
	public Category findById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No category with id " + id));
	}


	@Override
	public Category save(Category category) {
		return this.repository.save(category);
	}


	@Override
	public void delete(Long id) {
		 this.repository.deleteById(id);
	}

}
