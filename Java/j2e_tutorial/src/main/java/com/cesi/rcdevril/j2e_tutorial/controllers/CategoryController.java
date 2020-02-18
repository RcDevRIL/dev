package com.cesi.rcdevril.j2e_tutorial.controllers;


import java.util.List;

import com.cesi.rcdevril.j2e_tutorial.entities.Category;
import com.cesi.rcdevril.j2e_tutorial.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/categories/{id}", produces = "application/json")
    public ResponseEntity<Category> getOne(@PathVariable(value = "id") Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return category == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping(path = "/categories", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Category> post(@RequestBody Category category) {
        category = categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping(path = "/categories/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Category> put(@RequestBody Category category) {
        category = categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping(path = "/categories/{id}", produces = "application/json")
    public ResponseEntity<Category> delete(@PathVariable(value = "id") Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
