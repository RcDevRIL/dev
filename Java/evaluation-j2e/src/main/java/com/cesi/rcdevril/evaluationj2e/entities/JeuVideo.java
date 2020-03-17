package com.cesi.rcdevril.evaluationj2e.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class JeuVideo {

    @Id
    @GeneratedValue
    private Long id;


    @NotBlank(message = "Ce champ est obligatoire")
    private String name;

    private LocalDate dateDeSortie;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Bibliotheque.class)
    @JoinColumn(name="bibliotheque_id", referencedColumnName = "id", nullable = false)
    private Bibliotheque bibliotheque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateDeSortie() {
        return dateDeSortie;
    }

    public void setDateDeSortie(LocalDate dateDeSortie) {
        this.dateDeSortie = dateDeSortie;
    }

    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }
}
