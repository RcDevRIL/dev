package fr.cesi.mostuselesswebsitesapi.service;

import java.util.List;

import fr.cesi.mostuselesswebsitesapi.entity.Category;

public interface CategoryService {
	
	 /**
     * Récupère toutes les catégories de la bdd
     * @return Liste des catégories
     */
    public List<Category> findAll();

    /**
     * Récupère la catégorie dont l'identifiant est celui passé en paramètre
     * @param id Clé primaire de la catégorie
     * @return Category
     */
	public Category findById(Long id);

	public Category save(Category category);

	public void delete(Long id);


}
