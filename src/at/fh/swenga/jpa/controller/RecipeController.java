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
import at.fh.swenga.jpa.model.CommentModel;
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

		if (principal == null) {

			List<RecipeModel> recipes = recipeRepository.findAll();
			model.addAttribute("recipes", recipes);

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			List<RecipeModel> filteredRecipes = recipeRepository.filterRecipesByUserPreferences(user.getId());
			List<RecipeModel> orderedRecipes = recipeRepository.findRecipesOrderedByfavoriteIngredients(user.getId());

			List<RecipeModel> recipes = new ArrayList<RecipeModel>();

			for (RecipeModel recipe : orderedRecipes) {
				if (filteredRecipes.contains(recipe)) {
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
	public String reateNewRecipe(Model model, Principal principal, @RequestParam String description,
			@RequestParam String title) {

		Date now = new Date();
		String authorName = principal.getName();
		UserModel author = userRepository.findUserByUserName(authorName);

		RecipeModel newRecipeModel = new RecipeModel(now, now, title, description, author, true, true);

		recipeRepository.save(newRecipeModel);

		return "forward:/recipeList";
	}

	// Spring 4: @RequestMapping(value = "/searchRecipes", method =
	// RequestMethod.GET)
	@GetMapping("/searchRecipes")
	public String searchRecipes(Model model, @RequestParam String searchString, Principal principal) {

		if (principal == null) {

			List<RecipeModel> recipes = recipeRepository.findRecipesBySearchString(searchString);
			model.addAttribute("recipes", recipes);

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			List<RecipeModel> filteredRecipes = recipeRepository
					.filterRecipesByUserPreferencesAndSearchString(user.getId(), searchString);
			List<RecipeModel> orderedRecipes = recipeRepository.findRecipesOrderedByfavoriteIngredients(user.getId());

			List<RecipeModel> recipes = new ArrayList<RecipeModel>();

			for (RecipeModel recipe : orderedRecipes) {
				if (filteredRecipes.contains(recipe)) {
					recipes.add(recipe);
				}
			}
			model.addAttribute("recipes", recipes);
		}
		return "recipeList";
	}

	// Spring 4: @RequestMapping(value = "/likeRecipe", method =
	// RequestMethod.POST)
	@PostMapping("/likeRecipe")
	public String likeRecipe(Model model, @RequestParam int id, Principal principal) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (recipe == null) {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/recipeList";
		}
		
		if (recipe.getLikingUsers().contains(user)) {
			
			user.removeLikedRecipe(recipe);
			userRepository.save(user);
			
			recipe.removeLikingUser(user);
			
		} else {
			
			user.addLikedRecipe(recipe);
			userRepository.save(user);

			recipe.addLikingUser(user);
			
		}

		model.addAttribute("recipe", recipe);

		return "recipe";
	}
}
