package fr.cesi.mostuselesswebsitesapi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.mostuselesswebsitesapi.entity.Category;
import fr.cesi.mostuselesswebsitesapi.service.CategoryService;


@RestController
@RequestMapping(path="categories")
public class CategoryController {

	private final CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping("")
	public ResponseEntity<?>  findAll(){
		List<Category> categories =  this.service.findAll();
		if (categories != null)
			return ResponseEntity.ok().body(categories);
		else
			return ResponseEntity.badRequest().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findByName(@PathVariable Long id){
		Category category =        this.service.findById(id);
		if (category != null)
			return ResponseEntity.ok().body(category);
		else
			return ResponseEntity.badRequest().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Category category){
		Category saved = this.service.save(category);
		if (saved != null)
			return ResponseEntity.ok().body(saved);
		else
			return ResponseEntity.badRequest().build(); 
	}

	@DeleteMapping("/{id}")	
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			this.service.delete(id);
			return ResponseEntity.ok().build();
		} catch(EmptyResultDataAccessException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("/add-category")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {
		Category saved =     this.service.save(category);
		if (saved != null)
			return ResponseEntity.ok().body(saved);
		else
			return ResponseEntity.badRequest().build(); 
	}

}
