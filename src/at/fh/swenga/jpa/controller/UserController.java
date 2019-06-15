package at.fh.swenga.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.RecipeCollectionRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.dao.UserRoleRepository;
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
			newUserModel.addUserRole(userRole);
			newUserModel.setEnabled(true);

			

			userRole.addUser(newUserModel);
			userRepository.save(newUserModel);

			// RecipeCollectionModel recipeCollection = new
			// RecipeCollectionModel("favorites", newUserModel);
			// recipeCollectionRepository.save(recipeCollection);

			model.addAttribute("message", "New user " + newUserModel.getUserName() + " added.");
		}

		return "login";
	}

	// Spring 4: @RequestMapping(value = "/showUser", method = RequestMethod.GET)
	@GetMapping("/showUser")
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

}
