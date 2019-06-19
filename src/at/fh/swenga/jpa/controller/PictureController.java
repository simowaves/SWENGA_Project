package at.fh.swenga.jpa.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	 * Display the upload form
	 * 
	 * @param model
	 * @param videoId
	 * @return
	 */
	@RequestMapping(value = "/uploadRecipePicture", method = RequestMethod.GET)
	public String showuploadRecipePicture(Model model, @RequestParam("id") int recipeId) {
		model.addAttribute("recipeId", recipeId);
		return "uploadRecipePicture";
	}

	/**
	 * Save uploaded file to the database (as 1:1 relationship to recipe)
	 * 
	 * @param model
	 * @param recipeId
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadRecipePicture", method = RequestMethod.POST)
	public String uploadRecipePicture(Model model, @RequestParam("id") int recipeId,
			@RequestParam("myPicture") MultipartFile newPicture) {

		try {

			RecipeModel recipe = recipeRepository.findRecipeById(recipeId);
			
			if (recipe == null)
				throw new IllegalArgumentException("No recipe with id " + recipeId);

			// Already a picture available -> delete it
			if (recipe.getPicture() != null) {
				pictureRepository.delete(recipe.getPicture());
				// Don't forget to remove the relationship too
				recipe.setPicture(null);
			}

			// Create a new picture and set all available infos

			PictureModel picture = new PictureModel();
			picture.setContent(newPicture.getBytes());
			picture.setContentType(newPicture.getContentType());
			picture.setCreated(new Date());
			picture.setFilename(newPicture.getOriginalFilename());
			picture.setName(newPicture.getName());
			recipe.setPicture(picture);
			recipeRepository.save(recipe);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}
		return "forward:/recipeList";
		//return "forward:/showRecipe?id="+recipeId;
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
