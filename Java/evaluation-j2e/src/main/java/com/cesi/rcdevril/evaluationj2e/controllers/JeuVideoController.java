package com.cesi.rcdevril.evaluationj2e.controllers;

import com.cesi.rcdevril.evaluationj2e.entities.Bibliotheque;
import com.cesi.rcdevril.evaluationj2e.entities.JeuVideo;
import com.cesi.rcdevril.evaluationj2e.repostories.JeuVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("jeuvideo")
public class JeuVideoController {

    @Autowired
    private JeuVideoRepository jeuVideoRepository;


    @GetMapping("/")
    public ResponseEntity<List<JeuVideo>> getJeuVideo() {
        List<JeuVideo> b = jeuVideoRepository.findAll();
        return b == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok().body(b);
    }

    @GetMapping(path = "/{id:^[0-9]+$}", produces = "application/json")
    public ResponseEntity<JeuVideo> getOne(@PathVariable(value = "id") Long id) {
        JeuVideo jeuVideo = jeuVideoRepository.findById(id).orElse(null);
        return jeuVideo == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok().body(jeuVideo);
    }

    @GetMapping("/{search:[A-Za-z]+}")
    public ResponseEntity<?> findAllThatContains(@PathVariable String search) {
        return ResponseEntity.ok().body(jeuVideoRepository.findByNameContaining(search));
    }
}
