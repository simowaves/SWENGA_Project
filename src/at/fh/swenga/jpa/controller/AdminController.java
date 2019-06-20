package at.fh.swenga.jpa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.PictureModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class AdminController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	// Spring 4: @RequestMapping(value = "/showAdminUsers", method = RequestMethod.GET)
	@GetMapping("/showAdminUsers")
	public String showAdminUsers(Model model) {
		List<UserModel> users = userRepository.findUsersWithUserRolesAndRecipesAndComments();
		model.addAttribute("users", users);
		return "adminUsers";
	}
	
	// Spring 4: @RequestMapping(value = "/showAdminRecipes", method = RequestMethod.GET)
	@GetMapping("/showAdminRecipes")
	public String showAdminRecipes(Model model) {
		List<RecipeModel> recipes = recipeRepository.findRecipesWithReportingUsers();
		model.addAttribute("recipes", recipes);
		return "adminRecipes";
	}
	
}
