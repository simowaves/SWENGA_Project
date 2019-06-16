package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.CommentModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class CommentController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;

	// Spring 4: @RequestMapping(value = "/postComment", method = RequestMethod.POST)
	@PostMapping("/postComment")
	public String postComment(Model model, @RequestParam int id, Principal principal, @RequestParam String content) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);
		Date now = new Date();
		String authorName = principal.getName();
		UserModel author = userRepository.findUserByUserName(authorName);

		
		
		CommentModel commentModel = new CommentModel(content, now);
		commentRepository.save(commentModel);
		
		commentModel.setAuthor(author);
		commentRepository.save(commentModel);
		
		commentModel.setRecipe(recipe);
		commentRepository.save(commentModel);
		
		recipe.addComment(commentModel);
		
		if (recipe != null) {
			model.addAttribute("recipe", recipe);
		} else {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "forward:/recipeList";
		}
		
		return "recipe";
	}

}
