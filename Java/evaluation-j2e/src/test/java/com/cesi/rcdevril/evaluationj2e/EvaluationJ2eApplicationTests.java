package com.cesi.rcdevril.evaluationj2e;

import com.cesi.rcdevril.evaluationj2e.entities.Bibliotheque;
import com.cesi.rcdevril.evaluationj2e.entities.JeuVideo;
import com.cesi.rcdevril.evaluationj2e.repostories.BibliothequeRepository;
import com.cesi.rcdevril.evaluationj2e.repostories.JeuVideoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
class EvaluationJ2eApplicationTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    JeuVideoRepository jeuVideoRepository;

    @Autowired
    BibliothequeRepository bibliothequeRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void createJeuVideo() {
        // given
        JeuVideo jeuVideo = new JeuVideo();
        jeuVideo.setId((long) 1);
        jeuVideo.setName("jeu vidéo de test");
        jeuVideo.setBibliotheque(new Bibliotheque());
        entityManager.persist(jeuVideo);
        entityManager.flush();
        // when
        Optional<JeuVideo> found = jeuVideoRepository.findById(jeuVideo.getId());
        // then
        if (found.isPresent()) {
            Assert.assertEquals("La création de jeu vidéo n'a pas marché !", jeuVideo.getId(), found.get().getId());
        } else {
            Assert.assertTrue("La création de jeu vidéo n'a pas marché !", false);
        }
    }
    @Test
    void createBibliotheque() {
        // given
        Bibliotheque bibliotheque = new Bibliotheque();
        entityManager.persist(bibliotheque);
        entityManager.flush();
        // when
        Optional<Bibliotheque> found = bibliothequeRepository.findById(bibliotheque.getId());
        // then
        if (found.isPresent()) {
            Assert.assertEquals("La création de bibliothèque n'a pas marché !", bibliotheque.getId(), found.get().getId());
        } else {
            Assert.assertTrue("La création de bibliothèque n'a pas marché !", false);
        }

    }

}
