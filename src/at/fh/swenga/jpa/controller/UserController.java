package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.fh.swenga.jpa.dao.AllergieRepository;
import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.RecipeCollectionRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.dao.UserRoleRepository;
import at.fh.swenga.jpa.model.AllergieModel;
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
	RecipeRepository recipeRepository;

	@Autowired
	IngredientRepository ingredientRepository;

	@Autowired
	AllergieRepository allergieRepository;

	// login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

	// register
	@GetMapping("/register")
	public String showRegister(Model model) {
		return "register";
	}

	// register
	@PostMapping("/register")
	public String register(@Valid UserModel newUserModel, BindingResult bindingResult, Model model) {

		// Any errors? -> Create a String out of all errors and return to the page
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			return "register";
		}

		// Look for user in the database. One available -> Error
		UserModel user = userRepository.findUserByUserName(newUserModel.getUserName());

		if (user != null) {
			model.addAttribute("errorMessage", "UserName already exists!<br>");
			return "register";
		} else {

			UserRoleModel userRole = userRoleRepository.findUserRoleByRole("ROLE_USER");
			if (userRole == null) {
				userRole = new UserRoleModel("ROLE_USER");
				userRoleRepository.save(userRole);
			}
				
			newUserModel.encryptPassword();
			newUserModel.setEnabled(true);
			userRepository.save(newUserModel);

			newUserModel.addUserRole(userRole);
			userRepository.save(newUserModel);

			RecipeCollectionModel recipeCollection = new RecipeCollectionModel("Favorites");
			recipeCollectionRepository.save(recipeCollection);

			newUserModel.addRecipeCollection(recipeCollection);

			recipeCollection.setUser(newUserModel);
			recipeCollectionRepository.save(recipeCollection);

			model.addAttribute("message", "New user " + newUserModel.getUserName() + " added.");
		}

		return "login";
	}

	//show user profile
	@GetMapping({ "/showUser"})
	public String showUserDetails(Model model, @RequestParam int id, Principal principal) {

		UserModel user = userRepository.findUserById(id);
		List<RecipeModel> recipes = recipeRepository.findRecipesByUserId(id);
		List<RecipeModel> likedRecipes = recipeRepository.findRecipesByLikingUserId(id);
		List<RecipeCollectionModel> collections = recipeCollectionRepository.findCollectionsByUserId(id);
		if (principal != null) {
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
		}

		if (user != null) {
			
			model.addAttribute("user", user);
			model.addAttribute("recipes", recipes);
			model.addAttribute("likedRecipes", likedRecipes);
			model.addAttribute("collections", collections);
			return "userInfo";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user " + id);
			return "errorPage";
		}
	}

	// add or remove user from the follower user
	@PostMapping("/followUser")
	public String likeRecipe(Model model, @RequestParam int id, Principal principal, RedirectAttributes redirectAttributes) {

		UserModel shownUser = userRepository.findUserById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithUsersIFollow(userName);

		if (shownUser == null) {
			model.addAttribute("errorMessage", "Couldn't find user " + id);
			return "errorPage";
		}

		if (userRepository.findUserIFollowFromUser(id, user.getId()) != null) {
			user.removeUserIFollow(shownUser);
			userRepository.save(user);
		} else {
			user.addUserIFollow(shownUser);
			userRepository.save(user);
		}

		redirectAttributes.addAttribute("id", id);

		return "redirect:/showUser";
	}

	//show logged in user profile
	@GetMapping("/showCurrentUser")
	public String showCurrentUser(Model model, Principal principal) {

		String userName = principal.getName();		
		UserModel user = userRepository.findUserByUserName(userName);
		

		if (user != null) {
			List<RecipeModel> recipes = recipeRepository.findRecipesByUserId(user.getId());
			List<RecipeModel> likedRecipes = recipeRepository.findRecipesByLikingUserId(user.getId());
			List<RecipeCollectionModel> collections = recipeCollectionRepository.findCollectionsByUserId(user.getId());
			
			model.addAttribute("user", user);
			model.addAttribute("recipes", recipes);
			model.addAttribute("likedRecipes", likedRecipes);
			model.addAttribute("collections", collections);
			
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
			
			return "userInfo";
		} else {
			model.addAttribute("errorMessage", "You are not logged in");
			return "errorPage";
		}
	}

	// show user preferences from logged in user
	@GetMapping("/showCurrentUserPreferences")
	public String likeRecipe(Model model, Principal principal) {
		List<IngredientModel> ingredients = ingredientRepository.findAllIngredientsOrderByName();
		model.addAttribute("ingredients", ingredients);
		List<AllergieModel> allergies = allergieRepository.findAllAllergiesOrderByName();
		model.addAttribute("allergies", allergies);

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithAllergiesAndLovedIngredientsAndHatedIngredients(userName);

		if (user != null) {
			model.addAttribute("user", user);
			
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
			
			return "userPreferences";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user ");
			return "errorPage";
		}
	}
	
	// show account settings from logged in user
	@GetMapping("/showCurrentUserAccountSettings")
	public String showCurrentUserAccountSettings(Model model, Principal principal) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (user != null) {
			
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
			
			model.addAttribute("user", user);
			return "accountSettings";
		} else {
			model.addAttribute("errorMessage", "Couldn't find user ");
			return "errorPage";
		}
	}

	// add an allergy to the user
	@PostMapping("/addAllergy")
	public String addAllergy(@RequestParam int allergy, Principal principal, Model model) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithAllergies(userName);
		AllergieModel allergieModel = allergieRepository.findAllergieById(allergy);
		user.addAllergie(allergieModel);
		userRepository.save(user);
		
		return "redirect:/showCurrentUserPreferences";
	}
	
	// remove an allergy from the user
	@PostMapping("/removeAllergy")
	public String removeAllergy(@RequestParam int allergyId, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithAllergies(userName);
		AllergieModel allergieModel = allergieRepository.findAllergieById(allergyId);
		
		user.removeAllergie(allergieModel);
		userRepository.save(user);

		return "redirect:/showCurrentUserPreferences";
	}
	
	// add a loved ingredient to the user
	@PostMapping("/addLovedIngredient")
	public String addLovedIngredient(@RequestParam int ingredient, Principal principal, Model model) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithLovedIngredients(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.addLovedIngredient(ingredientModel);
		userRepository.save(user);
		
		return "redirect:/showCurrentUserPreferences";
	}
	
	// remove a loved ingredient from the user
	@PostMapping("/removeLovedIngredient")
	public String removeLovedIngredient(@RequestParam int ingredient, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithLovedIngredients(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.removeLovedIngredient(ingredientModel);
		userRepository.save(user);
		
		return "redirect:/showCurrentUserPreferences";
	}
	
	// add a hated ingredient to the user
	@PostMapping("/addHatedIngredient")
	public String addHatedIngredient(@RequestParam int ingredient, Principal principal, Model model) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithHatedIngredients(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.addHatedIngredient(ingredientModel);
		userRepository.save(user);

		
		return "redirect:/showCurrentUserPreferences";
	}
	
	// remove a hated ingredient from the user
	@PostMapping("/removeHatedIngredient")
	public String removeHatedIngredient(@RequestParam int ingredient, Principal principal, Model model) {

		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithHatedIngredients(userName);
		IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
		user.removeHatedIngredient(ingredientModel);
		userRepository.save(user);
		
		return "redirect:/showCurrentUserPreferences";
	}
	
	// change the username
	@PostMapping("/updateUserName")
	public String updateUserName(@RequestParam String userName, Principal principal, Model model) {
		String oldUserName = principal.getName();
		UserModel user = userRepository.findUserByUserName(oldUserName);
		user.setUserName(userName);
		userRepository.save(user);
		SecurityContextHolder.clearContext();
	
		return "redirect:/login";
	}
	
	// change email address
	@PostMapping("/updateEmailAddress")
	public String updateEmailAddress(@RequestParam String emailAddress, Principal principal, Model model) {
		String oldUserName = principal.getName();
		UserModel user = userRepository.findUserByUserName(oldUserName);
		user.setEmailAddress(emailAddress);
		userRepository.save(user);
	
		return "redirect:/showCurrentUserAccountSettings";
	}
	
	// change the password
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam String password, Principal principal, Model model) {
		String oldUserName = principal.getName();
		UserModel user = userRepository.findUserByUserName(oldUserName);
		user.setPassword(password);
		user.encryptPassword();

		userRepository.save(user);
	
		return "redirect:/showCurrentUserAccountSettings";
	}
	
	// delete a user
	@PostMapping("/deleteUser")
	public String deleteUser(Model model, Principal principal) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		user.setEnabled(false);
		userRepository.save(user);
		List<RecipeModel> recipes = recipeRepository.findRecipesByUserId(user.getId());
		for (RecipeModel recipe : recipes) {
			recipe.setEnabled(false);
			recipeRepository.save(recipe);
		}
		SecurityContextHolder.clearContext();
		
		return "login"; 
	}
}
