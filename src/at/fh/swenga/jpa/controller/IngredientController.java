package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class IngredientController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	// Spring 4: @RequestMapping(value = "/showIngredient", method = RequestMethod.GET)
		@GetMapping("/showIngredient")
		public String showIngredient(Model model, @RequestParam int id, Principal principal) {
			
			if (principal == null) {
				
				List<RecipeModel> recipes = recipeRepository.findRecipesByIngredientId(id);
				model.addAttribute("recipes", recipes);

			} else {
				
				UserModel user = userRepository.findUserByUserName(principal.getName());
				
				List<RecipeModel> filteredRecipes = recipeRepository.filterRecipesByUserPreferencesAndIngredientId(user.getId(), id);
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

}