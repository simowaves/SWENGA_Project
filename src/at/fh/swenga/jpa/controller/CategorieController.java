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
	PictureRepository pictureRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CategorieRepository categorieRepository;
	
	// Spring 4: @RequestMapping(value = "/showCategorie", method = RequestMethod.GET)
	@GetMapping("/showCategorie")
	public String showCategorie(Model model, @RequestParam int id, Principal principal) {
		
		if (principal == null) {
			
			List<RecipeModel> recipes = recipeRepository.findRecipesByCategorieId(id);
			model.addAttribute("recipes", recipes);

		} else {
			
			UserModel user = userRepository.findUserByUserName(principal.getName());
			List<RecipeModel> recipes = recipeRepository.findRecipesFilteredByUserPreferencesAndCategorie(user.getId(), id);
			
			model.addAttribute("recipes", recipes);
		}
		return "recipeList";
	}
	
	// Spring 4: @RequestMapping(value = "/categoriesList", method = RequestMethod.GET)
	@GetMapping("/categoriesList")
	public String categoriesList(Model model) {
		

			List<CategorieModel> categories = categorieRepository.findAll();
			
			model.addAttribute("categories", categories);
			
		return "/categoriesList";
	}

}
