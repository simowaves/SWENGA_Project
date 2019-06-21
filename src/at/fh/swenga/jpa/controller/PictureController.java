package at.fh.swenga.jpa.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.model.PictureModel;
import at.fh.swenga.jpa.model.RecipeModel;
import at.fh.swenga.jpa.model.UserModel;

@Controller
public class PictureController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	/**
	 * Save uploaded file to the database (as 1:1 relationship to recipe)
	 * 
	 * @param model
	 * @param recipeId
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/changeRecipePicture", method = RequestMethod.POST)
	public String uploadRecipePicture(Model model, @RequestParam("id") int recipeId,
			@RequestParam("recipePicture") MultipartFile newPicture, RedirectAttributes redirectAttributes) {

		try {

			RecipeModel recipe = recipeRepository.findRecipeById(recipeId);
			
			if (recipe == null)
				throw new IllegalArgumentException("No recipe with id " + recipeId);
			PictureModel picture;
			// Already a picture available -> change it
			if (recipe.getPicture() != null) {
				picture = recipe.getPicture();
			} else {
				picture = new PictureModel();
			}

			// Create a new picture and set all available infos

			picture.setContent(newPicture.getBytes());
			picture.setContentType(newPicture.getContentType());
			picture.setCreated(new Date());
			picture.setFilename(newPicture.getOriginalFilename());
			picture.setName(newPicture.getName());
			recipe.setPicture(picture);
			recipeRepository.save(recipe);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", recipeId);
		return "redirect:/showRecipe";
	}
	
	
	
	//USER Profile Pictures
	/**
	 * Save uploaded file to the database (as 1:1 relationship to recipe)
	 * 
	 * @param model
	 * @param recipeId
	 * @param file
	 * @return
	 */
	
	@RequestMapping(value = "/changeUserPicture", method = RequestMethod.POST)
	public String uploadUserPicture(Model model, @RequestParam("id") int userId,
			@RequestParam("userPicture") MultipartFile newPicture, RedirectAttributes redirectAttributes) {

		try {
			UserModel user = userRepository.findUserById(userId);
			
			if (user == null)
				throw new IllegalArgumentException("No recipe with id " + userId);
			PictureModel picture;
			// Already a picture available -> change it
			if (user.getPicture() != null) {
				picture = user.getPicture();
			} else {
				picture = new PictureModel();
			}

			// Create a new picture and set all available infos
			picture.setContent(newPicture.getBytes());
			picture.setContentType(newPicture.getContentType());
			picture.setCreated(new Date());
			picture.setFilename(newPicture.getOriginalFilename());
			picture.setName(newPicture.getName());
			user.setPicture(picture);
			userRepository.save(user);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
			return "errorPage";
		}
		redirectAttributes.addAttribute("id", userId);
		return "redirect:/showUser";
	}
	
	
	/*

	@RequestMapping("/download")
	public void download(@RequestParam("documentId") int documentId, HttpServletResponse response) {

		Optional<DocumentModel> docOpt = documentRepository.findById(documentId);
		if (!docOpt.isPresent())
			throw new IllegalArgumentException("No document with id " + documentId);

		DocumentModel doc = docOpt.get();

		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getFilename() + "\"");
			OutputStream out = response.getOutputStream();
			// application/octet-stream
			response.setContentType(doc.getContentType());
			out.write(doc.getContent());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
}
