package com.cesi.rcdevril.j2e_tutorial.controllers;

import java.util.List;


import com.cesi.rcdevril.j2e_tutorial.entities.Website;
import com.cesi.rcdevril.j2e_tutorial.repositories.WebsiteRepository;
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
public class WebsiteController {

    @Autowired
    private WebsiteRepository websiteRepository;

    @GetMapping("/websites")
    public List<Website> getAll() {
        return websiteRepository.findAll();
    }

    @GetMapping(path = "/websites/{id}", produces="application/json")
    public ResponseEntity<Website> getOne(@PathVariable(value = "id") Long id) {
        Website website = websiteRepository.findById(id).orElse(null);
        if(website == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(website, HttpStatus.OK);
    }

    @GetMapping(path = "/categories/{id}/websites", produces="application/json")
    public ResponseEntity<List<Website>> getOneByCategory(@PathVariable(value = "id") Long id) {
        List<Website> websites = websiteRepository.findByCategoryId(id);
        if(websites.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(websites, HttpStatus.OK);
    }

    @PostMapping(path = "/websites", consumes = "application/json", produces="application/json")
    public ResponseEntity<Website> post(@RequestBody Website website) {
        website = websiteRepository.saveAndFlush(website);

        return new ResponseEntity<>(website, HttpStatus.CREATED);
    }

    @PutMapping(path = "/websites/{id}", consumes = "application/json", produces="application/json")
    public ResponseEntity<Website> put(@RequestBody Website website) {
        website = websiteRepository.saveAndFlush(website);

        return new ResponseEntity<>(website, HttpStatus.OK);
    }

    @DeleteMapping(path = "/websites/{id}", produces="application/json")
    public ResponseEntity<Website> delete(@PathVariable(value = "id") Long id) {
        try {
            websiteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}