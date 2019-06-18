package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.jpa.dao.CategorieRepository;
import at.fh.swenga.jpa.dao.IngredientAmountRepository;
import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.CategorieModel;
import at.fh.swenga.jpa.model.IngredientAmountModel;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.PictureModel;
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
	IngredientAmountRepository ingredientAmountRepository;

	@Autowired
	CategorieRepository categorieRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/", "list", "recipeList" })
	public String index(Model model, Principal principal) {

		if (principal == null) {

			List<RecipeModel> recipes = recipeRepository.findRecipesOrderedByLikes();
			model.addAttribute("recipes", recipes);

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			if (user == null) {
				List<RecipeModel> recipes = recipeRepository.findAll();
				model.addAttribute("recipes", recipes);
			} else {
				List<RecipeModel> filteredRecipes = recipeRepository.filterRecipesByUserPreferences(user.getId());
				List<RecipeModel> orderedRecipes = recipeRepository
						.findRecipesOrderedByfavoriteIngredients(user.getId());

				List<RecipeModel> recipes = new ArrayList<RecipeModel>();

				for (RecipeModel recipe : orderedRecipes) {
					if (filteredRecipes.contains(recipe)) {
						recipes.add(recipe);
					}
				}
				model.addAttribute("recipes", recipes);
			}
		}
		return "recipeList";
	}

	// Spring 4: @RequestMapping(value = "/showRecipe", method = RequestMethod.GET)
	@GetMapping({ "/showRecipe", "/likeRecipe", "/reportRecipe" })
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

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/createRecipe")
	public String openCreateForm(Model model) {
		List<IngredientModel> ingredients = ingredientRepository.findAllIngredientsOrderByName();
		model.addAttribute("ingredients", ingredients);
		List<CategorieModel> categories = categorieRepository.findAllCategoriesOrderByName();
		model.addAttribute("categories", categories);
		return "createRecipe";
	}

	@PostMapping(value = "/createRecipe")

	public String createNewRecipe(Model model, Principal principal, @RequestParam String description,
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

	// Spring 4: @RequestMapping(value = "/reportRecipe", method =
	// RequestMethod.POST)
	@PostMapping("/reportRecipe")
	public String reportRecipe(Model model, @RequestParam int id, Principal principal) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);

		if (recipe == null) {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/recipeList";
		}

		if (recipe.getReportingUsers().contains(user)) {

			user.removeReportedRecipe(recipe);
			userRepository.save(user);

			recipe.removeReportingUser(user);

		} else {

			user.addReportedRecipe(recipe);
			userRepository.save(user);

			recipe.addReportingUser(user);

		}

		model.addAttribute("recipe", recipe);

		return "recipe";
	}
	

	//GetMethode in PictureController geben?
	/*//Spring 4: @RequestMapping(value = "/upload", method = RequestMethod.GET)
	@GetMapping("/upload")
	public String showUploadForm(Model model, @RequestParam("id") int recipeId) {
		model.addAttribute("recipeId", recipeId);
		return "uploadFile";
	}*/

	// Spring 4: @RequestMapping(value = "/createNewRecipe", method = RequestMethod.POST)
	@PostMapping("/createNewRecipe")
	public String createNewRecipe(@RequestParam String title, @RequestParam String description,
			@RequestParam String amount, @RequestParam String ingredient, @RequestParam String category,
			Principal principal, Model model) {

		RecipeModel newRecipeModel = new RecipeModel();
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		Date now = new Date();
		String[] amountValues = amount.split(",");
		String[] ingredientValues = ingredient.split(",");
		String[] categoryValues = category.split(",");
		Set<CategorieModel> categorySet = new HashSet<CategorieModel>();

		for (int j = 0; j < categoryValues.length; j++) {
			CategorieModel categoryModel = categorieRepository.findCategorieById(Integer.valueOf(categoryValues[j]));
			categorySet.add(categoryModel);
		}
/*
		//Try&Catch ist notwendig
		try {
			PictureModel picture = new PictureModel();
			picture.setContent(file.getBytes());
			picture.setContentType(file.getContentType());
			picture.setCreated(now);
			picture.setFilename(file.getOriginalFilename());
			picture.setName(file.getName());
			newRecipeModel.setPicture(picture);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}
*/
		newRecipeModel.setTitle(title);
		newRecipeModel.setDescription(description);
		newRecipeModel.setCreateDate(now);
		newRecipeModel.setLastEdited(now);
		newRecipeModel.setCategories(categorySet);
		recipeRepository.save(newRecipeModel);
		newRecipeModel.setAuthor(user);
		recipeRepository.save(newRecipeModel);

		for (int i = 0; i < amountValues.length; i++) {
			IngredientModel ingredientModel = ingredientRepository
					.findIngredientById(Integer.valueOf(ingredientValues[i]));
			IngredientAmountModel ingredientAmountModel = new IngredientAmountModel(amountValues[i]);
			ingredientAmountRepository.save(ingredientAmountModel);
			ingredientAmountModel.setIngredient(ingredientModel);
			ingredientAmountModel.setRecipe(newRecipeModel);
			ingredientAmountRepository.save(ingredientAmountModel);
			newRecipeModel.addIngredientAmount(ingredientAmountModel);
		}

		model.addAttribute("recipe", newRecipeModel);
		return "recipe";
	}
}
