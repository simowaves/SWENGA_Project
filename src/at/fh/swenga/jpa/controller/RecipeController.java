package at.fh.swenga.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.jpa.dao.DocumentRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.model.RecipeModel;

public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		//List<RecipeModel> recipes = recipeRepository.findAll();
		//model.addAttribute("recipes", recipes);
		return "index";
	}
	
}
