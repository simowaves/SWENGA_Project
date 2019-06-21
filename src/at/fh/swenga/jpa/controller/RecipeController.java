package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.fh.swenga.jpa.dao.CategorieRepository;
import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.IngredientAmountRepository;
import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.RecipeCollectionRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.CategorieModel;
import at.fh.swenga.jpa.model.CommentModel;
import at.fh.swenga.jpa.model.IngredientAmountModel;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.PictureModel;
import at.fh.swenga.jpa.model.RecipeCollectionModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	IngredientRepository ingredientRepository;

	@Autowired
	IngredientAmountRepository ingredientAmountRepository;

	@Autowired
	CategorieRepository categorieRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	RecipeCollectionRepository recipeCollectionRepository;

	// Landing page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String langingPage(Model model, Principal principal) {

		if (principal == null) {

			List<RecipeModel> allRecipes = recipeRepository.findRecipesOrderedByLikes();
			List<RecipeModel> recipes = new ArrayList<RecipeModel>();
			for (RecipeModel recipe : allRecipes) {
				recipes.add(recipe);
				if(recipes.size() >= 3) break;
			}

			List<CategorieModel> categories = categorieRepository.findTop3ByOrderByTitleAsc();
			List<UserModel> allAuthors = userRepository.findUsersOrderedByMostRecipes();
			List<UserModel> authors = new ArrayList<UserModel>();
			for (UserModel author : allAuthors) {
				authors.add(author);
				if(authors.size() >= 3) break;
			}
			List<IngredientModel> ingredients = ingredientRepository.findTop3ByOrderById();

			model.addAttribute("recipes", recipes);
			model.addAttribute("authors", authors);
			model.addAttribute("categories", categories);
			model.addAttribute("ingredients", ingredients);
			return "landing";

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			if (user == null) {
				List<RecipeModel> allRecipes = recipeRepository.findRecipesOrderedByLikes();
				List<RecipeModel> recipes = new ArrayList<RecipeModel>();
				for (RecipeModel recipe : allRecipes) {
					recipes.add(recipe);
					if(recipes.size() >= 3) break;
				}

				List<CategorieModel> categories = categorieRepository.findTop3ByOrderByTitleAsc();
				List<UserModel> allAuthors = userRepository.findUsersOrderedByMostRecipes();
				List<UserModel> authors = new ArrayList<UserModel>();
				for (UserModel author : allAuthors) {
					authors.add(author);
					if(authors.size() >= 3) break;
				}
				List<IngredientModel> ingredients = ingredientRepository.findTop3ByOrderById();

				model.addAttribute("recipes", recipes);
				model.addAttribute("authors", authors);
				model.addAttribute("categories", categories);
				model.addAttribute("ingredients", ingredients);
				return "landing";
			} else {

				UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
				model.addAttribute("loggedInUser", loggedInUser);

				List<RecipeModel> allRecipes = recipeRepository.findRecipesFilteredByUserPreferences(user.getId());
				List<RecipeModel> recipes = new ArrayList<RecipeModel>();
				for (RecipeModel recipe : allRecipes) {
					recipes.add(recipe);
					if(recipes.size() >= 3) break;
				}

				List<CategorieModel> categories = categorieRepository.findTop3ByOrderByTitleAsc();
				List<UserModel> allAuthors = userRepository.findUsersOrderedByMostRecipes();
				List<UserModel> authors = new ArrayList<UserModel>();
				for (UserModel author : allAuthors) {
					authors.add(author);
					if(authors.size() >= 3) break;
				}
				List<IngredientModel> ingredients = ingredientRepository.findTop3ByOrderById();

				model.addAttribute("recipes", recipes);
				model.addAttribute("authors", authors);
				model.addAttribute("categories", categories);
				model.addAttribute("ingredients", ingredients);
				return "landing";
			}
		}

	}

	// INDEX
	@RequestMapping(value = {"recipeList" })
	public String index(Model model, Principal principal) {

		model.addAttribute("header", "All Recipes");

		if (principal == null) {

			List<RecipeModel> recipes = recipeRepository.findRecipesOrderedByLikes();
			model.addAttribute("recipes", recipes);

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			if (user == null) {
				List<RecipeModel> recipes = recipeRepository.findRecipesOrderedByLikes();
				model.addAttribute("recipes", recipes);
			} else {

				UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
				model.addAttribute("loggedInUser", loggedInUser);

				List<RecipeModel> recipes = recipeRepository.findRecipesFilteredByUserPreferences(user.getId());
				model.addAttribute("recipes", recipes);
			}
		}
		return "recipeList";
	}

	// show newest recipes 
	@RequestMapping(value = { "mostRecentRecipes" })
	public String recentRecipes(Model model, Principal principal) {

		model.addAttribute("header", "Most Recent Recipes");

		if (principal == null) {

			List<RecipeModel> recipes = recipeRepository.findRecipesOrderedByLastEdited();
			model.addAttribute("recipes", recipes);

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			if (user == null) {
				List<RecipeModel> recipes = recipeRepository.findRecipesOrderedByLastEdited();
				model.addAttribute("recipes", recipes);
			} else {

				UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
				model.addAttribute("loggedInUser", loggedInUser);

				List<RecipeModel> recipes = recipeRepository
						.findRecipesFilteredByUserPreferencesOrderedByLastEdited(user.getId());
				model.addAttribute("recipes", recipes);
			}
		}
		return "recipeList";
	}

	// show recipes from followed users
	@RequestMapping(value = { "/followingRecipes" })
	public String followingRecipes(Model model, Principal principal) {

		model.addAttribute("header", "Recipes By Friends");

		if (principal == null) {
			model.addAttribute("errorMessage", "No User logged in.");
			return "errorPage";

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			if (user == null) {
				model.addAttribute("errorMessage", "No User logged in.");
				return "errorPage";
			} else {

				UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
				model.addAttribute("loggedInUser", loggedInUser);

				List<RecipeModel> recipes = recipeRepository
						.findRecipesFilteredByUserPreferencesAndFollowedByUserOrderedByLastEdited(user.getId());
				
				model.addAttribute("recipes", recipes);
			}
		}
		return "recipeList";
	}

	// show recipe with all information
	@GetMapping({ "/showRecipe"})
	public String showRecipeDetails(Model model, @RequestParam int id, Principal principal) {

		RecipeModel recipe = recipeRepository.findRecipeByIdWithPictureAndLikingUsers(id);

		if (recipe != null) {
			List<IngredientAmountModel> ingredientAmounts = ingredientAmountRepository
					.findIngredientAmountsByRecipeId(id);
			model.addAttribute("ingredientAmounts", ingredientAmounts);
			List<CommentModel> comments = commentRepository.findCommentsByRecipeId(id);
			model.addAttribute("comments", comments);
			model.addAttribute("recipe", recipe);
			if (principal != null) {
				String userName = principal.getName();
				UserModel user = userRepository.findUserByUserNameWithAllergies(userName);
				if (user != null) {
					UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
					model.addAttribute("loggedInUser", loggedInUser);
					int userId = user.getId();
					List<RecipeCollectionModel> collections = recipeCollectionRepository
							.findCollectionsByUserId(userId);
					model.addAttribute("collections", collections);
					
					Set<UserModel> likingUsers = recipe.getLikingUsers();
					boolean liked;
					if(likingUsers.contains(loggedInUser)) {
						liked = true;
					} else liked = false;
					
					
					model.addAttribute("liked", liked);
				}
			}

			return "recipe";
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "errorPage";
		}
	}

	// create a new recipe
	@GetMapping(value = "/createRecipe")
	public String openCreateForm(Model model, Principal principal) {

		UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
		model.addAttribute("loggedInUser", loggedInUser);
		List<IngredientModel> ingredients = ingredientRepository.findAllIngredientsOrderByName();
		model.addAttribute("ingredients", ingredients);
		List<CategorieModel> categories = categorieRepository.findAllCategoriesOrderByName();
		model.addAttribute("categories", categories);
		return "createRecipe";
	}

	// search for recipe
	@GetMapping("/searchRecipes")
	public String searchRecipes(Model model, @RequestParam String searchString, Principal principal) {

		if (principal == null) {

			List<RecipeModel> recipes = recipeRepository.findRecipesBySearchString(searchString);
			model.addAttribute("recipes", recipes);

		} else {

			UserModel user = userRepository.findUserByUserName(principal.getName());

			if (user == null) {
				List<RecipeModel> recipes = recipeRepository.findRecipesBySearchString(searchString);
				model.addAttribute("recipes", recipes);
			} else {
				UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
				model.addAttribute("loggedInUser", loggedInUser);

				List<RecipeModel> recipes = recipeRepository
						.findRecipesFilteredByUserPreferencesAndSearchString(user.getId(), searchString);

				model.addAttribute("recipes", recipes);
			}

		}
		return "recipeList";
	}

	// like a recipe
	@PostMapping("/likeRecipe")
	public String likeRecipe(Model model, @RequestParam int id, Principal principal,
			RedirectAttributes redirectAttributes) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithLikedRecipes(userName);

		if (recipe == null) {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "errorPage";
		}

		if (userRepository.findLikingUserFromRecipe(id, user.getId()) != null) {

			user.removeLikedRecipe(recipe);
			userRepository.save(user);

		} else {

			user.addLikedRecipe(recipe);
			userRepository.save(user);
		}
		redirectAttributes.addAttribute("id", id);

		return "redirect:/showRecipe";
	}

	// report a recipe
	@PostMapping("/reportRecipe")
	public String reportRecipe(Model model, @RequestParam int id, Principal principal,
			RedirectAttributes redirectAttributes) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserNameWithReportedRecipes(userName);

		if (recipe == null) {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "errorPage";
		}

		if (userRepository.findReportingUserFromRecipe(id, user.getId()) != null) {

			user.removeReportedRecipe(recipe);
			userRepository.save(user);
		} else {

			user.addReportedRecipe(recipe);
			userRepository.save(user);
		}

		redirectAttributes.addAttribute("id", id);

		return "redirect:/showRecipe";
	}

	// create a new recipe
	@PostMapping("/createNewRecipe")
	public String createNewRecipe(@RequestParam String title, @RequestParam String description,
			@RequestParam String amount, @RequestParam String ingredient, @RequestParam String category,
			@RequestParam int publish, Principal principal, Model model,
			@RequestParam("recipePicture") MultipartFile file, RedirectAttributes redirectAttributes) {

		RecipeModel newRecipeModel = new RecipeModel();
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		Date now = new Date();
		String[] amountValues = amount.split(",");
		String[] ingredientValues = ingredient.split(",");
		String[] categoryValues = category.split(",");
		Set<CategorieModel> categorySet = new HashSet<CategorieModel>();
		Boolean pusblishedRecipe = false;

		for (int j = 0; j < categoryValues.length; j++) {
			CategorieModel categoryModel = categorieRepository.findCategorieById(Integer.valueOf(categoryValues[j]));
			categorySet.add(categoryModel);
		}

		if (publish == 1) {
			pusblishedRecipe = true;
		}

		if (!file.isEmpty()) {
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
				return "errorPage";
			}
		}

		newRecipeModel.setTitle(title);
		newRecipeModel.setDescription(description);
		newRecipeModel.setCreateDate(now);
		newRecipeModel.setLastEdited(now);
		newRecipeModel.setCategories(categorySet);
		newRecipeModel.setEnabled(true);
		newRecipeModel.setPublished(pusblishedRecipe);

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

		System.out.print(publish);
		// model.addAttribute("recipe", newRecipeModel);
		redirectAttributes.addAttribute("id", newRecipeModel.getId());
		return "redirect:/showRecipe";
	}

	// delete a recipe
	@PostMapping("/deleteRecipe")
	public String deleteRecipe(Model model, @RequestParam int id, Principal principal) {
		RecipeModel recipeModel = recipeRepository.findRecipeById(id);
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			recipeModel.setEnabled(false);
			recipeRepository.save(recipeModel);
			return "redirect:/recipeList";
		} else {
			model.addAttribute("errorMessage", "You can't delete this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
	}

	// edit a recipe
	@GetMapping("/editRecipe")
	public String editRecipe(Model model, Principal principal, @RequestParam int id) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		RecipeModel recipeModel = recipeRepository.findRecipeById(id);
		List<IngredientModel> ingredientModel = ingredientRepository.findAllIngredientsOrderByName();
		List<CategorieModel> categoryModel = categorieRepository.findAllCategoriesOrderByName();
		List<IngredientAmountModel> ingredientAmountModel = ingredientAmountRepository
				.findIngredientAmountsByRecipeId(id);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			model.addAttribute("user", user);
			model.addAttribute("recipe", recipeModel);
			model.addAttribute("ingredients", ingredientModel);
			model.addAttribute("categories", categoryModel);
			model.addAttribute("ingredientAmounts", ingredientAmountModel);
	
			UserModel loggedInUser = userRepository.findUserByUserName(principal.getName());
			model.addAttribute("loggedInUser", loggedInUser);
			return "editRecipe";
		} else {
			model.addAttribute("errorMessage", "You can't edit this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
	}

	// add a new ingredient with amount to a recipe
	@PostMapping("/addIngredientAndAmount")
	public String addIngredientAndAmount(Model model, @RequestParam int recipeId, @RequestParam int ingredient,
			@RequestParam String amount, RedirectAttributes redirectAttributes, Principal principal) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeById(recipeId);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			IngredientAmountModel ingredientAmountModel = new IngredientAmountModel();
			IngredientModel ingredientModel = ingredientRepository.findIngredientById(ingredient);
			ingredientAmountModel.setAmount(amount);
			ingredientAmountRepository.save(ingredientAmountModel);
			ingredientAmountModel.setRecipe(recipeModel);
			ingredientAmountModel.setIngredient(ingredientModel);
			ingredientAmountRepository.save(ingredientAmountModel);
		} else {
			model.addAttribute("errorMessage", "You can't add ingredients to this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}

		redirectAttributes.addAttribute("id", recipeId);

		return "redirect:/editRecipe";
	}

	// remove a new ingredient with amount from a recipe
	@PostMapping("/removeIngredientAndAmount")
	public String removeIngredientAndAmount(Model model, @RequestParam int recipeId, @RequestParam int ingredientAmountId,
			@RequestParam String amount, Principal principal, RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeByIdWithIngredientAmounts(recipeId);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			IngredientAmountModel ingredientAmountModel = ingredientAmountRepository
					.findIngredientAmountsById(ingredientAmountId);
			recipeModel.removeIngredientAmount(ingredientAmountModel);
			recipeRepository.save(recipeModel);
	
			ingredientAmountRepository.deleteById(ingredientAmountId);
		
		} else {
			model.addAttribute("errorMessage", "You can't remove ingredients to this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", recipeId);

		return "redirect:/editRecipe";
	}

	// add a new category to a recipe
	@PostMapping("/addCategory")
	public String addCategory(Model model, @RequestParam int category, @RequestParam int recipeId, Principal principal,
			RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeById(recipeId);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			CategorieModel categorieModel = categorieRepository.findCategorieById(category);
			recipeModel.addCategorie(categorieModel);
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "You can't add categories to this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}

		redirectAttributes.addAttribute("id", recipeId);

		return "redirect:/editRecipe";
	}

	// remove a category from a recipe
	@PostMapping("/removeCategory")
	public String removeCategory(Model model, @RequestParam int category, @RequestParam int recipeId, Principal principal,
			RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeById(recipeId);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			CategorieModel categorieModel = categorieRepository.findCategorieById(category);
			recipeModel.removeCategorie(categorieModel);
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "You can't remove the categories from this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", recipeId);

		return "redirect:/editRecipe";
	}

	// set a new title
	@PostMapping("/setTitle")
	public String setTitle(Model model, @RequestParam int recipeId, @RequestParam String title, Principal principal,
			RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeById(recipeId);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			recipeModel.setTitle(title);
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "You can't set the Title to this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", recipeId);
		return "redirect:/editRecipe";
	}

	// set a new desciption
	@PostMapping("/setDescription")
	public String setDescription(Model model, @RequestParam int recipeId, @RequestParam String description, Principal principal,
			RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeById(recipeId);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			recipeModel.setDescription(description);
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "You can't set the Description for this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", recipeId);
		return "redirect:/editRecipe";
	}

	// Change the privacy from a recipe from public to private and vice versa
	@PostMapping("/changePublished")
	public String changePublished(Model model, @RequestParam int id, Principal principal, RedirectAttributes redirectAttributes) {
		String userName = principal.getName();
		UserModel user = userRepository.findUserByUserName(userName);
		
		RecipeModel recipeModel = recipeRepository.findRecipeById(id);
		if (user.getId() == recipeModel.getAuthor().getId()) {
			if (recipeModel.isPublished() == true) {
				recipeModel.setPublished(false);
			} else {
				recipeModel.setPublished(true);
			}
	
			recipeRepository.save(recipeModel);
		} else {
			model.addAttribute("errorMessage", "You can't change the publicity options for this recipe, because it doesn't belong to you!! ");
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", id);
		return "redirect:/showRecipe";
	}
}
