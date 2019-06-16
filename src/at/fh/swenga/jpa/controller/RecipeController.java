package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "list", "recipeList" })
	public String index(Model model, Principal principal) {
		
		String userName;
		
		if (principal != null) {
			userName = principal.getName();
		}else userName = "test";
		
		UserModel user = userRepository.findUserByUserName(userName);
		
		if (user == null) {
			List<RecipeModel> recipes = recipeRepository.findAll();
			model.addAttribute("recipes", recipes);
		} else {
			
			List<RecipeModel> filteredRecipes = recipeRepository.filterRecipesByUserPreferences(user.getId());
			List<RecipeModel> orderedRecipes = recipeRepository.findRecipesOrderedByfavoriteIngredients(user.getId());
			
			List<RecipeModel> recipes = new ArrayList<RecipeModel>();

	        for (RecipeModel recipe : orderedRecipes) {
	            if(filteredRecipes.contains(recipe)) {
	                recipes.add(recipe);
	            }
	        }
			model.addAttribute("recipes", recipes);
		}

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
	@GetMapping(value = "/createRecipe")
	public String openCreateForm(Model model) {
		List<IngredientModel> ingredients = ingredientRepository.findAll();
		model.addAttribute("ingredients", ingredients);
		return "createRecipe";
	}
	
	@PostMapping(value = "/createRecipe")
	public String reateNewRecipe(Model model, Principal principal, @RequestParam String description, @RequestParam String title) {
		
		Date now = new Date();
		String authorName = principal.getName();
		UserModel author = userRepository.findUserByUserName(authorName);
		
		RecipeModel newRecipeModel = new RecipeModel(now, now, title, description, author, true, true);
		
		recipeRepository.save(newRecipeModel);
		
		return "forward:/recipeList";
	}

	
}
