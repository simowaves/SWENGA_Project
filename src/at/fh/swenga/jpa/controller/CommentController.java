package at.fh.swenga.jpa.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.fh.swenga.jpa.dao.CommentRepository;
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
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;

	// it posts a comment for a recipe
	@PostMapping("/postComment")
	public String postComment(Model model, @RequestParam int id, Principal principal, @RequestParam String content, RedirectAttributes redirectAttributes) {

		RecipeModel recipe = recipeRepository.findRecipeById(id);
		Date now = new Date();
		String authorName = principal.getName();
		UserModel author = userRepository.findUserByUserName(authorName);
		
		if (recipe == null) {
			model.addAttribute("errorMessage", "Couldn't find recipe " + id);
			return "errorPage";
		}
		
		CommentModel commentModel = new CommentModel(content, now);
		commentRepository.save(commentModel);
		
		commentModel.setAuthor(author);
		commentModel.setRecipe(recipe);
		
		commentRepository.save(commentModel);
		
		redirectAttributes.addAttribute("id", id);
		
		return "redirect:/postComment";
	}

}
