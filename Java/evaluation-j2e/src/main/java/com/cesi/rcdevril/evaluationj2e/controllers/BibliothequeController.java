package com.cesi.rcdevril.evaluationj2e.controllers;

import com.cesi.rcdevril.evaluationj2e.entities.Bibliotheque;
import com.cesi.rcdevril.evaluationj2e.repostories.BibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bibliotheques")
public class BibliothequeController {

    @Autowired
    BibliothequeRepository bibliothequeRepository;

    @GetMapping("/")
    public ResponseEntity<List<Bibliotheque>> getBibliotheque() {
        List<Bibliotheque> b = bibliothequeRepository.findAll();
        return b == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok().body(b);
    }
    @GetMapping(path = "/{id:^[0-9]+$}", produces="application/json")
    public ResponseEntity<Bibliotheque> getOne(@PathVariable(value = "id") Long id) {
        Bibliotheque bibliotheque = bibliothequeRepository.findById(id).orElse(null);
        return bibliotheque == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok().body(bibliotheque);
    }

}
