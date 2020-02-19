package fr.cesi.mostuselesswebsitesweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Website {

	@Id
	@Column(name="url", nullable = false)
	@NotBlank(message = "Ce champ est obligatoire")
	private String url;

	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	@NotNull(message = "Ce champ est obligatoire")
	private Category category;
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
 
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	
}
