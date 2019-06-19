package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import at.fh.swenga.jpa.dao.AllergieRepository;
import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.RecipeCollectionRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.dao.UserRoleRepository;
import at.fh.swenga.jpa.model.AllergieModel;
import at.fh.swenga.jpa.model.CategorieModel;
import at.fh.swenga.jpa.model.IngredientAmountModel;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.RecipeCollectionModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;
import at.fh.swenga.jpa.model.UserRoleModel;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	RecipeCollectionRepository recipeCollectionRepository;

	@Autowired
	IngredientRepository ingredientRepository;

	@Autowired
	AllergieRepository allergieRepository;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

	// Spring 4: @RequestMapping(value = "/register", method = RequestMethod.GET)
	@GetMapping("/register")
	public String showRegister(Model model) {
		return "register";
	}

	// Spring 4: @RequestMapping(value = "/register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(@Valid UserModel newUserModel, BindingResult bindingResult, Model model) {

		// Any errors? -> Create a String out of all errors and return to the page
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			// Multiple ways to "forward"
			return "register";
		}

		// Look for user in the database. One available -> Error
		UserModel user = userRepository.findUserByUserName(newUserModel.getUserName());

		if (user != null) {
			model.addAttribute("errorMessage", "UserName already exists!<br>");
			return "register";
		} else {

			UserRoleModel userRole = userRoleRepository.findUserRoleByRole("ROLE_USER");
			if (userRole == null)
				userRole = new UserRoleModel("ROLE_USER");

			newUserModel.encryptPassword();
			newUserModel.setEnabled(true);
			userRepository.save(newUserModel);

			newUserModel.addUserRole(userRole);
			// userRepository.save(newUserModel);

			RecipeCollectionModel recipeCollection = new RecipeCollectionModel("Favorites");
			recipeCollectionRepository.save(recipeCollection);

			newUserModel.addRecipeCollection(recipeCollection);
			userRepository.save(newUserModel);

			recipeCollection.setUser(newUserModel);
			recipeCollectionRepository.save(recipeCollection);

			model.addAttribute("message", "New user " + newUserModel.getUserName() + " added.");
		}

		return "login";
	}

	// Spring 4: @RequestMapping(value = "/showUser", method = RequestMethod.GET)
	@GetMapping({ "/showUser", "/followUser" })
	public String showUserDetails(Model model, @RequestParam int id) {

		UserModel user = userRepository.findUserById(id);

		if (user != null) {
			model.addAttribute("user", user);
			return "userInfo";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user " + id);
			return "forward:/recipeList";
		}
	}

	@RequestMapping(value = "/loggedUserName", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		return authentication.getName();
	}

	// Spring 4: @RequestMapping(value = "/followUser", method =
	// RequestMethod.POST)
	@PostMapping("/followUser")
	public String likeRecipe(Model model, @RequestParam int id, Principal principal) {

		UserModel shownUser = userRepository.findUserById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (shownUser == null) {
			model.addAttribute("errorMessage", "Couldn't find user " + id);
			return "forward:/recipeList";
		}

		if (shownUser.getUsersFollowingMe().contains(user)) {

			user.removeUserIFollow(shownUser);
			userRepository.save(user);

			shownUser.removeUserFollowingMe(user);

		} else {

			user.addUserIFollow(shownUser);
			userRepository.save(user);

			shownUser.addUserFollowingMe(user);

		}

		model.addAttribute("user", shownUser);

		return "userInfo";
	}

	// Spring 4: @RequestMapping(value = "/showCurrentUser", method =
	// RequestMethod.GET)
	@GetMapping("/showCurrentUser")
	public String showCurrentUser(Model model, Principal principal) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (user != null) {
			model.addAttribute("user", user);
			return "userInfo";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user ");
			return "forward:/recipeList";
		}
	}

	// Spring 4: @RequestMapping(value = "/showCurrentUserPreferences", method =
	// RequestMethod.GET)
	@GetMapping("/showCurrentUserPreferences")
	public String likeRecipe(Model model, Principal principal) {
		List<IngredientModel> ingredients = ingredientRepository.findAllIngredientsOrderByName();
		model.addAttribute("ingredients", ingredients);
		List<AllergieModel> allergies = allergieRepository.findAllAllergiesOrderByName();
		model.addAttribute("allergies", allergies);

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (user != null) {
			model.addAttribute("user", user);
			return "userPreferences";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user ");
			return "forward:/recipeList";
		}
	}
	
	// Spring 4: @RequestMapping(value = "/showCurrentUserPreferences", method =
	// RequestMethod.GET)
	@PostMapping("/showCurrentUserPreferences")
	public String likeRecipePost(Model model, Principal principal) {
		List<IngredientModel> ingredients = ingredientRepository.findAllIngredientsOrderByName();
		model.addAttribute("ingredients", ingredients);
		List<AllergieModel> allergies = allergieRepository.findAllAllergiesOrderByName();
		model.addAttribute("allergies", allergies);

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (user != null) {
			model.addAttribute("user", user);
			return "userPreferences";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user ");
			return "forward:/recipeList";
		}
	}

	// Spring 4: @RequestMapping(value = "/addAllergy", method =
	// RequestMethod.POST)
	@PostMapping("/addAllergy")
	public String addAllergy(@RequestParam int allergy, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		AllergieModel allergieModel = allergieRepository.findAllergieById(allergy);
		user.addAllergie(allergieModel);
		
		return "/showCurrentUserPreferences";
	}
	
	// Spring 4: @RequestMapping(value = "/removeAllergy", method =
	// RequestMethod.POST)
	@PostMapping("/removeAllergy")
	public String removeAllergy(@RequestParam int allergyId, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		AllergieModel allergieModel = allergieRepository.findAllergieById(allergyId);
		user.removeAllergie(allergieModel);
		model.addAttribute("allergies", allergieModel);

		return "forward:/showCurrentUserPreferences";
	}
	
	// Spring 4: @RequestMapping(value = "/addLikedIngredient", method =
	// RequestMethod.POST)
	@PostMapping("/addLikedIngredient")
	public String addLikedIngredient(@RequestParam int ingredient, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.addLovedIngredient(ingredientModel);
		
		return "/showCurrentUserPreferences";
	}
	
	// Spring 4: @RequestMapping(value = "/removeLikedIngredient", method =
	// RequestMethod.POST)
	@PostMapping("/removeLikedIngredient")
	public String removeLikedIngredient(@RequestParam int ingredient, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.removeLovedIngredient(ingredientModel);
		
		return "/showCurrentUserPreferences";
	}
	
	// Spring 4: @RequestMapping(value = "/addHatedIngredient", method =
	// RequestMethod.POST)
	@PostMapping("/addHatedIngredient")
	public String addHatedIngredient(@RequestParam int ingredient, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.addHatedIngredient(ingredientModel);
		
		return "/showCurrentUserPreferences";
	}
	
	// Spring 4: @RequestMapping(value = "/removeHatedIngredient", method =
	// RequestMethod.POST)
	@PostMapping("/removeHatedIngredient")
	public String removeHatedIngredient(@RequestParam int ingredient, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.removeHatedIngredient(ingredientModel);
		
		return "/showCurrentUserPreferences";
	}
}
