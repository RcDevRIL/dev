package fr.cesi.mostuselesswebsitesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.mostuselesswebsitesapi.entity.Website;
import fr.cesi.mostuselesswebsitesapi.service.WebsiteService;


@RestController
@RequestMapping(path="websites")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class WebsiteController {

	private final WebsiteService service;

	@Autowired
	public WebsiteController(WebsiteService service) {
		this.service = service;
	}

	@GetMapping("/{categoryId:[0-9]+}")
	public ResponseEntity<?> findByCategory(@PathVariable Long categoryId){
		Website website = this.service.findByCategory(categoryId);
		if (website != null)
			return ResponseEntity.ok().body(website);
		else
			return ResponseEntity.badRequest().build();
	}

	@PostMapping("/add-website")
	public ResponseEntity<?> addWebsite(@RequestBody Website website) {
		Website saved = this.service.save(website);
		if (saved != null)
			return ResponseEntity.ok().body(website);
		else
			return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{search:[A-Za-z]+}")
	public ResponseEntity<?> findAllThatContains(@PathVariable String search){	
		return ResponseEntity.ok().body(this.service.findAllThatContains(search));
	}
}
