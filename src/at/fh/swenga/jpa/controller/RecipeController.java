package at.fh.swenga.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.model.RecipeModel;

@Controller
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	PictureRepository pictureRepository;

	@RequestMapping(value = { "/", "list", "recipeList" })
	public String index(Model model) {
		List<RecipeModel> recipes = recipeRepository.findAll();
		model.addAttribute("recipes", recipes);
		return "recipeList";
	}

	// Spring 4: @RequestMapping(value = "/showRecipe", method = RequestMethod.GET)
	@GetMapping("/showRecipe")
	public String showRecipeDetails(Model model, @RequestParam int id) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);

		if (recipe != null) {
			model.addAttribute("recipe", recipe);
			return "recipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/recipeList";
		}
	}

}
