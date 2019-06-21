package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.CategorieRepository;
import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.CategorieModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class CategorieController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategorieRepository categorieRepository;
	
	// shows a List of all recipes with a certain categorie
	@GetMapping("/showCategorie")
	public String showCategorie(Model model, @RequestParam int id, Principal principal) {
		
		if (principal == null) {
			
			List<RecipeModel> recipes = recipeRepository.findRecipesByCategorieId(id);
			model.addAttribute("recipes", recipes);

		} else {
		
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
			
			UserModel user = userRepository.findUserByUserName(principal.getName());
			List<RecipeModel> recipes = recipeRepository.findRecipesFilteredByUserPreferencesAndCategorie(user.getId(), id);
			
			model.addAttribute("recipes", recipes);
		}
		return "recipeList";
	}
	
	// shows a List of all categories
	@GetMapping("/categoriesList")
	public String categoriesList(Model model, Principal principal) {
		

			List<CategorieModel> categories = categorieRepository.findAll();
			
			model.addAttribute("categories", categories);
			
			if(principal != null) {
				UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
				model.addAttribute("loggedInUser", loggedInUser);
			}
			
		return "/categoriesList";
	}

}
