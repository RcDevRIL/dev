package com.cesi.rcdevril.evaluationj2e.controllers;

import com.cesi.rcdevril.evaluationj2e.entities.Bibliotheque;
import com.cesi.rcdevril.evaluationj2e.entities.JeuVideo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/alimenter")
public class HomeController {

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/")
    public void putTestData() {
        // Création de la bibliothèque
        Bibliotheque b = new Bibliotheque();
        b.setId((long) 1);
        // Ajout de 2 jeux
        JeuVideo jeuVideo1 = new JeuVideo();
        jeuVideo1.setId((long) 1);
        jeuVideo1.setName("TEST1");
        entityManager.persist(jeuVideo1);
        JeuVideo jeuVideo2 = new JeuVideo();
        jeuVideo2.setId((long) 2);
        jeuVideo2.setName("TEST2");
        entityManager.persist(jeuVideo2);
        b.addJeuVideo(jeuVideo1);
        b.addJeuVideo(jeuVideo2);
        entityManager.persist(b);
        entityManager.flush();
    }
}
