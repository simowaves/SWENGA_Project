package at.fh.swenga.jpa.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.jpa.dao.AllergieRepository;
import at.fh.swenga.jpa.dao.CategorieRepository;
import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.IngredientRepository;
import at.fh.swenga.jpa.dao.PictureRepository;
import at.fh.swenga.jpa.dao.RecipeCollectionRepository;
import at.fh.swenga.jpa.dao.RecipeRepository;
import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.dao.UserRoleRepository;
import at.fh.swenga.jpa.model.AllergieModel;
import at.fh.swenga.jpa.model.CategorieModel;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.UserModel;
import at.fh.swenga.jpa.model.UserRoleModel;


@Controller
public class TestDataController {
	
	@Autowired
	AllergieRepository allergieRepository;
	
	@Autowired
	CategorieRepository categorieRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	RecipeCollectionRepository collectionRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;
	
	

	@RequestMapping("/fillTestData")
	@Transactional
	public String fillData(Model model) {

		//----------------------------------------
		//-------------- USER_ROLE_MODEL
		//----------------------------------------
		UserRoleModel adminRole = userRoleRepository.findTop1ByRole("ROLE_ADMIN");
		if (adminRole == null)
			adminRole = new UserRoleModel("ROLE_ADMIN");

		UserRoleModel userRole = userRoleRepository.findTop1ByRole("ROLE_USER");
		if (userRole == null)
			userRole = new UserRoleModel("ROLE_USER");

		UserRoleModel godRole = userRoleRepository.findTop1ByRole("ROLE_GOD");
		if (godRole == null)
			godRole = new UserRoleModel("ROLE_GOD");
		
		
		//----------------------------------------
		//-------------- USER_MODEL 
		//-----------------------------------------
		UserModel tim = new UserModel("tim", "password", "tim@wtf.com", true);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		tim.addUserRole(godRole);
		userRepository.save(tim);
		
		UserModel julian = new UserModel("julian", "password", "julian@wtf.com", true);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		userRepository.save(julian);
		
		UserModel lukas = new UserModel("lukas", "password", "lukas@wtf.com", true);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		userRepository.save(lukas);
		
		UserModel simone = new UserModel("simone", "password", "simone@wtf.com", true);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		userRepository.save(simone);
		
		UserModel admin = new UserModel("admin", "password", "admin@wtf.com", true);
		admin.encryptPassword();
		admin.addUserRole(userRole);
		admin.addUserRole(adminRole);
		userRepository.save(admin);

		UserModel user = new UserModel("user", "password", "user@wtf.com", true);
		user.encryptPassword();
		user.addUserRole(userRole);
		userRepository.save(user);
		
		
		//----------------------------------------
		//-------------- ALLERGIE_MODEL
		//----------------------------------------
		AllergieModel all1 = new AllergieModel("Egg");
		allergieRepository.save(all1); 
		
		AllergieModel all2 = new AllergieModel("Peanut");
		allergieRepository.save(all2);
		
		AllergieModel all3 = new AllergieModel("Fish");
		allergieRepository.save(all3);
		
		AllergieModel all4 = new AllergieModel("Gluten");
		allergieRepository.save(all4);
		
		AllergieModel all5 = new AllergieModel("Shellfish");
		allergieRepository.save(all5);
		
		AllergieModel all6 = new AllergieModel("Lupin");
		allergieRepository.save(all6);
		
		AllergieModel all7 = new AllergieModel("Cow milk");
		allergieRepository.save(all7);
		
		AllergieModel all8 = new AllergieModel("Edible nuts");
		allergieRepository.save(all8);
		
		AllergieModel all9 = new AllergieModel("Sulfur dioxide");
		allergieRepository.save(all9);
		
		AllergieModel all10 = new AllergieModel("Celery");
		allergieRepository.save(all10);
		
		AllergieModel all11 = new AllergieModel("Mustard");
		allergieRepository.save(all11);
		
		AllergieModel all12 = new AllergieModel("Sesame seeds");
		allergieRepository.save(all12);
		
		AllergieModel all13 = new AllergieModel("Soybeans");
		allergieRepository.save(all13);
		
		AllergieModel all14 = new AllergieModel("Molluscs");
		allergieRepository.save(all14);
		
		
		//----------------------------------------
		//-------------- CATEGORIE_MODEL 
		//----------------------------------------
		CategorieModel cat1 = new CategorieModel("Italian");
		categorieRepository.save(cat1);
		
		CategorieModel cat2 = new CategorieModel("Breakfast");
		categorieRepository.save(cat2);
		
		CategorieModel cat3 = new CategorieModel("Lunch");
		categorieRepository.save(cat3);
		
		CategorieModel cat4 = new CategorieModel("Appetizer");
		categorieRepository.save(cat4);
		
		CategorieModel cat5 = new CategorieModel("Soup");
		categorieRepository.save(cat5);
		
		CategorieModel cat6 = new CategorieModel("Salad");
		categorieRepository.save(cat6);
		
		CategorieModel cat7 = new CategorieModel("Main dish");
		categorieRepository.save(cat7);
		
		CategorieModel cat8 = new CategorieModel("Side dish");
		categorieRepository.save(cat8);
		
		CategorieModel cat9 = new CategorieModel("Beef");
		categorieRepository.save(cat9);
		
		CategorieModel cat10 = new CategorieModel("Pork");
		categorieRepository.save(cat10);
		
		CategorieModel cat11 = new CategorieModel("Seafood");
		categorieRepository.save(cat11);
		
		CategorieModel cat12 = new CategorieModel("Poultry");
		categorieRepository.save(cat12);
		
		CategorieModel cat13 = new CategorieModel("Dessert");
		categorieRepository.save(cat13);
		
		CategorieModel cat14 = new CategorieModel("Holidays");
		categorieRepository.save(cat14);
		
		CategorieModel cat15 = new CategorieModel("Snack");
		categorieRepository.save(cat15);
		
		CategorieModel cat16 = new CategorieModel("Noodles");
		categorieRepository.save(cat16);
		
		CategorieModel cat17 = new CategorieModel("Pasta");
		categorieRepository.save(cat17);
		
		CategorieModel cat18 = new CategorieModel("Burger");
		categorieRepository.save(cat18);
		
		CategorieModel cat19 = new CategorieModel("Pie");
		categorieRepository.save(cat19);

		CategorieModel cat20 = new CategorieModel("Mince");
		categorieRepository.save(cat20);
		
		CategorieModel cat21 = new CategorieModel("Sausages");
		categorieRepository.save(cat21);
		
		CategorieModel cat22 = new CategorieModel("Chicken");
		categorieRepository.save(cat22);
		
		CategorieModel cat23 = new CategorieModel("Turkey");
		categorieRepository.save(cat23);
		
		CategorieModel cat24 = new CategorieModel("Duck");
		categorieRepository.save(cat24);
		
		CategorieModel cat25 = new CategorieModel("Lamb");
		categorieRepository.save(cat25);
		
		CategorieModel cat26 = new CategorieModel("Meat");
		categorieRepository.save(cat26);
		
		CategorieModel cat27 = new CategorieModel("Seafood");
		categorieRepository.save(cat27);
		
		CategorieModel cat28 = new CategorieModel("Stir Fry");
		categorieRepository.save(cat28);
		
		CategorieModel cat29 = new CategorieModel("Baking");
		categorieRepository.save(cat29);
		
		CategorieModel cat30 = new CategorieModel("Sauces");
		categorieRepository.save(cat30);
		
		//----------------------------------------
		//-------------- INGREDIENT_MODEL
		//----------------------------------------

		IngredientModel ing1 = new IngredientModel ("Onion", true, true);
		ingredientRepository.save(ing1);
		
		IngredientModel ing2 = new IngredientModel ("Rosemary", true, true);
		ingredientRepository.save(ing2);
		
		IngredientModel ing3 = new IngredientModel ("Bacon", false, false);
		ingredientRepository.save(ing3);
		
		IngredientModel ing4 = new IngredientModel ("Olive oil", true, true);
		ingredientRepository.save(ing4);
		
		IngredientModel ing5 = new IngredientModel ("Minced beef", false, false);
		ingredientRepository.save(ing5);
		
		IngredientModel ing6 = new IngredientModel ("Red wine", true, true);
		ingredientRepository.save(ing6); 
		
		IngredientModel ing7 = new IngredientModel ("Tomatoes", true, true);
		ingredientRepository.save(ing7);
		
		IngredientModel ing8 = new IngredientModel ("Plum tomatoes", true, true);
		ingredientRepository.save(ing8);
		
		IngredientModel ing9 = new IngredientModel ("Spaghetti", true, false); //so ein sauhaufen
		ingredientRepository.save(ing9);
		
		IngredientModel ing10 = new IngredientModel ("Parmesan cheese", true, false);
		ingredientRepository.save(ing10);
		
		IngredientModel ing11 = new IngredientModel ("Eggs", true, false);
		ingredientRepository.save(ing11);
		
		IngredientModel ing12 = new IngredientModel ("Cow milk", true, false);
		ingredientRepository.save(ing12);
		
		IngredientModel ing13 = new IngredientModel ("Flour", true, true);
		ingredientRepository.save(ing13);
		
		IngredientModel ing14 = new IngredientModel ("Sugar", true, true);
		ingredientRepository.save(ing14);
		
		IngredientModel ing15 = new IngredientModel ("Vegetable stock", true, true);
		ingredientRepository.save(ing15);
		
		IngredientModel ing16 = new IngredientModel ("Parsley", true, true);
		ingredientRepository.save(ing16);
		
		IngredientModel ing17 = new IngredientModel ("Vegetable oil", true, true);
		ingredientRepository.save(ing17);
		
		IngredientModel ing18 = new IngredientModel ("Pepper", true, true);
		ingredientRepository.save(ing18);
		
		IngredientModel ing19 = new IngredientModel ("Salt", true, true);
		ingredientRepository.save(ing19);
		
		IngredientModel ing20 = new IngredientModel ("Green pepper", true, true);
		ingredientRepository.save(ing20);
		
		IngredientModel ing21 = new IngredientModel ("Red pepper", true, true);
		ingredientRepository.save(ing21);
		
		IngredientModel ing22 = new IngredientModel ("Carrot", true, true);
		ingredientRepository.save(ing22);
		
		IngredientModel ing23 = new IngredientModel ("Prawn", false, false);
		ingredientRepository.save(ing23);
		
		IngredientModel ing24 = new IngredientModel ("Clams", false, false);
		ingredientRepository.save(ing24);
		
		IngredientModel ing25 = new IngredientModel ("Squid", false, false);
		ingredientRepository.save(ing25);
		
		IngredientModel ing26 = new IngredientModel ("Mussels", false, false);
		ingredientRepository.save(ing26);
		
		IngredientModel ing27 = new IngredientModel ("Rice", true, true);
		ingredientRepository.save(ing27);
		
		IngredientModel ing28 = new IngredientModel ("Garlic", true, true);
		ingredientRepository.save(ing28);
		
		IngredientModel ing29 = new IngredientModel ("Saffron", true, true);
		ingredientRepository.save(ing29);
		
		IngredientModel ing30 = new IngredientModel ("Water", true, true);
		ingredientRepository.save(ing30);
		
		IngredientModel ing31 = new IngredientModel ("Veal", false, false);
		ingredientRepository.save(ing31);
		
		IngredientModel ing32 = new IngredientModel ("Breadcrumbs", true, false);
		ingredientRepository.save(ing32);
		
		IngredientModel ing33 = new IngredientModel ("Butter", true, false);
		ingredientRepository.save(ing33);
		
		IngredientModel ing34 = new IngredientModel ("Lemon", true, true);
		ingredientRepository.save(ing34);
		
		IngredientModel ing35 = new IngredientModel ("Organic stock", true, true);
		ingredientRepository.save(ing35);
		
		IngredientModel ing36 = new IngredientModel ("Cellary", true, true);
		ingredientRepository.save(ing36);
		
		IngredientModel ing37 = new IngredientModel ("Risotto rice", true, true);
		ingredientRepository.save(ing37);
		
		IngredientModel ing38 = new IngredientModel ("Vermouth", true, true);
		ingredientRepository.save(ing38);
		
				
		
		//----------------------------------------
		//-------------- RECIPE_MODEL
		//----------------------------------------

		
		//----------------------------------------
		//-------------- COMMENT_MODEL
		//----------------------------------------

		
		
		//----------------------------------------
		//-------------- RECIPIE_COLLECTION_MODEL
		//----------------------------------------



		return "forward:login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

}
