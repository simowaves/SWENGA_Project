package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class AdminController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	// Open Admin Center: Users
	@GetMapping("/showAdminUsers")
	public String showAdminUsers(Model model, Principal principal) {
		List<UserModel> users = userRepository.findUsersWithUserRolesAndRecipesAndComments();
		model.addAttribute("users", users);
		if(principal != null) {
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
		}
		return "adminUsers";
	}
	
	// Open Admin Center: Recipes
	@GetMapping("/showAdminRecipes")
	public String showAdminRecipes(Model model, Principal principal) {
		List<RecipeModel> recipes = recipeRepository.findRecipesWithReportingUsers();
		model.addAttribute("recipes", recipes);
		if(principal != null) {
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
		}
		return "adminRecipes";
	}
	
	// Deletes all the reporting users from recipe
	@PostMapping("/clearRecipeReporters")
	public String clearRecipeReporters(Model model, @RequestParam int recId) {
		
		RecipeModel recipe = recipeRepository.findRecipeByIdWithReportingUsers(recId);
		List<UserModel> users = userRepository.findUsersThatReportedRecipe(recId);
		
		
		if (recipe == null) {
			model.addAttribute("errorMessage", "Couldn't find recipe " + recId);
			return "errorPage";
		}
	
		for (UserModel user : users) {
			user.removeReportedRecipe(recipe);
			userRepository.save(user);
		}
		
		return "redirect:/showAdminRecipes";
	}
	
}
