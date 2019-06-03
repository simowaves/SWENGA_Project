package at.fh.swenga.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;

import at.fh.swenga.jpa.dao.DocumentRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;

public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
}
