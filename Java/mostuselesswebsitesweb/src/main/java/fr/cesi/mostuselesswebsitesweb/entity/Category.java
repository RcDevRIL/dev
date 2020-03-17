package fr.cesi.mostuselesswebsitesweb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    @NotBlank(message = "Ce champ est obligatoire")
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    private Set<Website> websites;
	
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

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

	public Set<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(Set<Website> websites) {
		this.websites = websites;
	}
    
    
}
