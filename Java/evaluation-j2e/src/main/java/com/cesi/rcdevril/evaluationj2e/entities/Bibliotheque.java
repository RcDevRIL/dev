package com.cesi.rcdevril.evaluationj2e.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bibliotheque {

    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(
            mappedBy = "bibliotheque",
            fetch = FetchType.LAZY,
            targetEntity = JeuVideo.class
    )
    @JsonIgnore
    List<JeuVideo> jeuVideoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addJeuVideo(JeuVideo jeuVideo) {
        jeuVideoList.add(jeuVideo);
        jeuVideo.setBibliotheque(this);
    }

    public void removeJeuVideo(JeuVideo jeuVideo) {
        jeuVideoList.remove(jeuVideo);
        jeuVideo.setBibliotheque(null);
    }
}
