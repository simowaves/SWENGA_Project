package at.fh.swenga.jpa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.fh.swenga.jpa.dao.RecipeCollectionRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.RecipeCollectionModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class RecipeCollectionController {

	@Autowired
	RecipeCollectionRepository recipeCollectionRepository;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	UserRepository userRepository;

	// add a new recipe collection to the user
	@PostMapping("/addCollection")
	public String addCollection(@RequestParam String title, Principal principal, RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithCollections(userName);
		int userId = user.getId();
		RecipeCollectionModel recipeCollectionModel = new RecipeCollectionModel();
		recipeCollectionModel.setTitle(title);
		
		recipeCollectionRepository.save(recipeCollectionModel);
		
		recipeCollectionModel.setUser(user);
		recipeCollectionRepository.save(recipeCollectionModel);

		redirectAttributes.addAttribute("id", userId);
		return "redirect:/showCurrentUser";
	}
	
	// change the name of a recipe collection
	@PostMapping("/changeCollectionName")
	public String changeCollectionName(@RequestParam int collectionId, @RequestParam String title, Principal principal, RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithAllergies(userName);
		int userId = user.getId();
		RecipeCollectionModel recipeCollectionModel = recipeCollectionRepository.findCollectionsById(collectionId);
		recipeCollectionModel.setTitle(title);
		recipeCollectionRepository.save(recipeCollectionModel);
		userRepository.save(user);
		
		redirectAttributes.addAttribute("id", userId);
		return "redirect:/showCurrentUser";
	}
	
	
	// delete a collection from the user
	@PostMapping("/deleteCollection")
	public String deleteCollection(@RequestParam int collectionId, Principal principal, RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		int userId = user.getId();
		recipeCollectionRepository.deleteById(collectionId);
		
		redirectAttributes.addAttribute("id", userId);
		return "redirect:/showCurrentUser";
	}
	
	// add a recipe to a collection
	@PostMapping("/addRecipeToCollection")
	public String addRecipeToCollection(@RequestParam int collectionId, @RequestParam int id, Principal principal, RedirectAttributes redirectAttributes, Model model) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		int userId = user.getId();
		RecipeCollectionModel recipeCollectionModel = recipeCollectionRepository.findCollectionsByIdWithRecipesAndUser(collectionId);
		RecipeModel recipeModel = recipeRepository.findRecipeByIdWithCollections(id);
		if (recipeCollectionModel.getUser().getId() == userId) {
			recipeModel.addRecipeCollection(recipeCollectionModel);
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "This isn't your collection");
			return "errorPage";
		}
		
		
		redirectAttributes.addAttribute("id", id);
		return "redirect:/showRecipe";
	}
	
	// remove a recipe from a collection
	@PostMapping("/removeRecipeFromCollection")
	public String removeRecipeFromCollection(@RequestParam int collectionId, @RequestParam int recipeId, Principal principal, RedirectAttributes redirectAttributes, Model model) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		int userId = user.getId();
		RecipeCollectionModel recipeCollectionModel = recipeCollectionRepository.findCollectionsByIdWithRecipesAndUser(collectionId);
		RecipeModel recipeModel = recipeRepository.findRecipeByIdWithCollections(recipeId);
		if (recipeCollectionModel.getUser().getId() == userId) {
			recipeModel.removeRecipeCollection(recipeCollectionModel);
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "This isn't your collection");
			return "errorPage";
		}
		
		redirectAttributes.addAttribute("id", userId);
		return "redirect:/showCurrentUser";
	}
}
