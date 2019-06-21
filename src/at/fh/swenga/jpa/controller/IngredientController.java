package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class IngredientController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	// shows all recipes that include a certain ingredient
	@GetMapping("/showIngredient")
	public String showIngredient(Model model, @RequestParam int id, Principal principal) {
		
		if (principal == null) {
			
			List<RecipeModel> recipes = recipeRepository.findRecipesByIngredientId(id);
			model.addAttribute("recipes", recipes);

		} else {
			
			UserModel user = userRepository.findUserByUserName(principal.getName());

			List<RecipeModel> recipes = recipeRepository.findRecipesFilteredByUserPreferencesAndIngredient(user.getId(), id);
			
			model.addAttribute("recipes", recipes);
			
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
		}
		return "recipeList";
	}
		
	// shows a list with all Ingredients
	@GetMapping("/ingredientsList")
	public String categoriesList(Model model, Principal principal) {
		

		List<IngredientModel> ingredients = ingredientRepository.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		if(principal != null) {
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
		}
			
		return "ingredientsList";
	}

}