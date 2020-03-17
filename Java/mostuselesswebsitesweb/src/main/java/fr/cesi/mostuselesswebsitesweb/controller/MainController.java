package fr.cesi.mostuselesswebsitesweb.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cesi.mostuselesswebsitesweb.entity.Category;
import fr.cesi.mostuselesswebsitesweb.entity.Website;

@Controller
public class MainController {

	private static final String VUE_AJOUTER_CATEGORIE = "ajouter-categorie";
	private static final String VUE_AJOUTER_SITE = "ajouter-site";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
	    return builder.build();
	}

	@ModelAttribute("categories")
	public List<Category> populateCategories() {
		return Arrays.asList(restTemplate.getForEntity("http://localhost:9090/categories", Category[].class).getBody());
	}
	
	@ModelAttribute("website")
	public Website initWebsite() {
		return new Website();
	}

	@GetMapping("/")
	public String getCategories(Model model) {
		return "accueil";
	}

	@GetMapping("/ajouter-categorie")
	public String ajouterCategorie(Model model) {
		model.addAttribute("category", new Category());

		return VUE_AJOUTER_CATEGORIE;
	}

	@PostMapping("/ajouter-categorie")
	public String creerCategorie(@ModelAttribute @Valid Category category, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		// Validate result
		if (result.hasErrors()) {
			return "ajouter-categorie";
		}

		restTemplate.postForEntity("http://localhost:9090/categories/add-category", category, Category.class);

		redirectAttributes.addFlashAttribute("flashCategory", "success");

		return "redirect:/ajouter-categorie";
	}

	@GetMapping("/websites/{categoryId}")
	public String rechercherSiteWebParCategorie(@PathVariable Long categoryId, Model model) {

		model.addAttribute("category",
				restTemplate.getForEntity("http://localhost:9090/categories/" + categoryId, Category.class).getBody());

		model.addAttribute("website",
				restTemplate.getForEntity("http://localhost:9090/websites/" + categoryId, Website.class).getBody());

		return "categorie";
	}

	@GetMapping("/ajouter-site")
	public String ajouterSite(Model model) {
		return VUE_AJOUTER_SITE;
	}

	@PostMapping("/ajouter-site")
	public String creerCategorie(@ModelAttribute @Valid Website website, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		// Validate result
		if (result.hasErrors()) {
			return VUE_AJOUTER_SITE;
		}
		
		Category category = restTemplate
				.getForEntity("http://localhost:9090/categories/" + Long.valueOf(website.getCategory().getName()),
						Category.class)
				.getBody();
		website.setCategory(category);

		restTemplate.postForEntity("http://localhost:9090/websites/add-website", website, Website.class);

		redirectAttributes.addFlashAttribute("flashImage", "success");

		return "redirect:/ajouter-site";
	}
}
