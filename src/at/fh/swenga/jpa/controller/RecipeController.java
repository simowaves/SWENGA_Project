package at.fh.swenga.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;

@Controller
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	PictureRepository pictureRepository;
	
	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		//List<RecipeModel> recipes = recipeRepository.findAll();
		//model.addAttribute("recipes", recipes);
		return "login";
	}
	
}
