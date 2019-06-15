package at.fh.swenga.jpa.controller;

import java.util.Date;
import java.util.HashSet;
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
import at.fh.swenga.jpa.model.CommentModel;
import at.fh.swenga.jpa.model.IngredientAmountModel;
import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.RecipeCollectionModel;
import at.fh.swenga.jpa.model.RecipeModel;
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
	RecipeCollectionRepository recipeCollectionRepository;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@RequestMapping("/fillTestData")
	@Transactional
	public String fillData(Model model) {

		// ----------------------------------------
		// -------------- USER_ROLE_MODEL
		// ----------------------------------------

		UserRoleModel adminRole = userRoleRepository.findTop1ByRole("ROLE_ADMIN");
		if (adminRole == null)
			adminRole = new UserRoleModel("ROLE_ADMIN");

		UserRoleModel userRole = userRoleRepository.findTop1ByRole("ROLE_USER");
		if (userRole == null)
			userRole = new UserRoleModel("ROLE_USER");

		UserRoleModel godRole = userRoleRepository.findTop1ByRole("ROLE_GOD");
		if (godRole == null)
			godRole = new UserRoleModel("ROLE_GOD");
		
		// ----------------------------------------
		// -------------- ALLERGIE_MODEL
		// ----------------------------------------

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
		
		// ----------------------------------------
		// -------------- INGREDIENT_MODEL
		// ----------------------------------------
		
		IngredientModel ing1 = new IngredientModel("Onion", true, true);
		ingredientRepository.save(ing1);

		IngredientModel ing2 = new IngredientModel("Rosemary", true, true);
		ingredientRepository.save(ing2);

		IngredientModel ing3 = new IngredientModel("Bacon", false, false);
		ingredientRepository.save(ing3);

		IngredientModel ing4 = new IngredientModel("Olive oil", true, true);
		ingredientRepository.save(ing4);

		IngredientModel ing5 = new IngredientModel("Minced beef", false, false);
		ingredientRepository.save(ing5);

		IngredientModel ing6 = new IngredientModel("Red wine", true, true);
		ingredientRepository.save(ing6);

		IngredientModel ing7 = new IngredientModel("Tomatoes", true, true);
		ingredientRepository.save(ing7);

		IngredientModel ing8 = new IngredientModel("Plum tomatoes", true, true);
		ingredientRepository.save(ing8);
				
		Set<AllergieModel> allSet1 = new HashSet<AllergieModel>();
		allSet1.add(all4);

		IngredientModel ing9 = new IngredientModel("Spaghetti", true, false, allSet1);
		ingredientRepository.save(ing9);
			
		Set<AllergieModel> allSet2 = new HashSet<AllergieModel>();
		allSet2.add(all7);
				
		IngredientModel ing10 = new IngredientModel("Parmesan cheese", true, false, allSet2);
		ingredientRepository.save(ing10);
				
		Set<AllergieModel> allSet3 = new HashSet<AllergieModel>();
		allSet3.add(all1);

		IngredientModel ing11 = new IngredientModel("Eggs", true, false, allSet3);
		ingredientRepository.save(ing11);
				
		Set<AllergieModel> allSet4 = new HashSet<AllergieModel>();
		allSet4.add(all7);

		IngredientModel ing12 = new IngredientModel("Cow milk", true, false, allSet4);
		ingredientRepository.save(ing12);
				
		Set<AllergieModel> allSet5 = new HashSet<AllergieModel>();
		allSet5.add(all4);

		IngredientModel ing13 = new IngredientModel("Flour", true, true, allSet5);
		ingredientRepository.save(ing13);

		IngredientModel ing14 = new IngredientModel("Sugar", true, true);
		ingredientRepository.save(ing14);

		IngredientModel ing15 = new IngredientModel("Vegetable stock", true, true);
		ingredientRepository.save(ing15);

		IngredientModel ing16 = new IngredientModel("Parsley", true, true);
		ingredientRepository.save(ing16);

		IngredientModel ing17 = new IngredientModel("Vegetable oil", true, true);
		ingredientRepository.save(ing17);

		IngredientModel ing18 = new IngredientModel("Pepper", true, true);
		ingredientRepository.save(ing18);

		IngredientModel ing19 = new IngredientModel("Salt", true, true);
		ingredientRepository.save(ing19);

		IngredientModel ing20 = new IngredientModel("Green pepper", true, true);
		ingredientRepository.save(ing20);

		IngredientModel ing21 = new IngredientModel("Red pepper", true, true);
		ingredientRepository.save(ing21);

		IngredientModel ing22 = new IngredientModel("Carrot", true, true);
		ingredientRepository.save(ing22);
				
		Set<AllergieModel> allSet6 = new HashSet<AllergieModel>();
		allSet6.add(all5);

		IngredientModel ing23 = new IngredientModel("Prawn", false, false, allSet6);
		ingredientRepository.save(ing23);
				
		Set<AllergieModel> allSet7 = new HashSet<AllergieModel>();
		allSet7.add(all14);

		IngredientModel ing24 = new IngredientModel("Clams", false, false, allSet7);
		ingredientRepository.save(ing24);
				
		Set<AllergieModel> allSet8 = new HashSet<AllergieModel>();
		allSet8.add(all4);

		IngredientModel ing25 = new IngredientModel("Squid", false, false, allSet8);
		ingredientRepository.save(ing25);
				
		Set<AllergieModel> allSet9 = new HashSet<AllergieModel>();
		allSet9.add(all14);

		IngredientModel ing26 = new IngredientModel("Mussels", false, false);
		ingredientRepository.save(ing26);

		IngredientModel ing27 = new IngredientModel("Rice", true, true);
		ingredientRepository.save(ing27);

		IngredientModel ing28 = new IngredientModel("Garlic", true, true);
		ingredientRepository.save(ing28);

		IngredientModel ing29 = new IngredientModel("Saffron", true, true);
		ingredientRepository.save(ing29);

		IngredientModel ing30 = new IngredientModel("Water", true, true);
		ingredientRepository.save(ing30);

		IngredientModel ing31 = new IngredientModel("Veal schnitzel", false, false);
		ingredientRepository.save(ing31);
				
		Set<AllergieModel> allSet10 = new HashSet<AllergieModel>();
		allSet10.add(all4);
		allSet10.add(all7);

		IngredientModel ing32 = new IngredientModel("Breadcrumbs", true, false, allSet10);
		ingredientRepository.save(ing32);
				
		Set<AllergieModel> allSet11 = new HashSet<AllergieModel>();
		allSet11.add(all7);

		IngredientModel ing33 = new IngredientModel("Butter", true, false, allSet11);
		ingredientRepository.save(ing33);

		IngredientModel ing34 = new IngredientModel("Lemon", true, true);
		ingredientRepository.save(ing34);

		IngredientModel ing35 = new IngredientModel("Organic stock", true, true);
		ingredientRepository.save(ing35);

		IngredientModel ing36 = new IngredientModel("Cellary", true, true);
		ingredientRepository.save(ing36);

		IngredientModel ing37 = new IngredientModel("Risotto rice", true, true);
		ingredientRepository.save(ing37);
				
		IngredientModel ing38 = new IngredientModel("Vermouth", true, true);
		ingredientRepository.save(ing38);

		// ----------------------------------------
		// -------------- USER_MODEL
		// ----------------------------------------
		
		Set<AllergieModel> allSetUser1 = new HashSet<AllergieModel>();
		allSetUser1.add(all2);
		allSetUser1.add(all3);
		allSetUser1.add(all6);
		
		/*
		Set<IngredientModel> lovedIngUser1 = new HashSet<IngredientModel>();
		lovedIngUser1.add(ing1);
		lovedIngUser1.add(ing5);
		lovedIngUser1.add(ing10);
		lovedIngUser1.add(ing15);
		lovedIngUser1.add(ing20);
		lovedIngUser1.add(ing25);
		lovedIngUser1.add(ing30);
		lovedIngUser1.add(ing35);
		*/
		
		Set<IngredientModel> hatedIngUser1 = new HashSet<IngredientModel>();
		hatedIngUser1.add(ing8);
		hatedIngUser1.add(ing27);
		hatedIngUser1.add(ing38);

		UserModel tim = new UserModel("tim", "password", "tim@wtf.com", true, allSetUser1);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		tim.addUserRole(godRole);
		/*
		tim.addLovedIngredient(ing1);
		tim.addLovedIngredient(ing5);
		tim.addLovedIngredient(ing10);
		tim.addLovedIngredient(ing15);
		tim.addLovedIngredient(ing20);
		tim.addLovedIngredient(ing25);
		
		tim.addHatedIngredient(ing38);
		tim.addHatedIngredient(ing8);
		tim.addHatedIngredient(ing27);
		*/
		userRepository.save(tim);
		
		Set<AllergieModel> allSetUser2 = new HashSet<AllergieModel>();
		allSetUser2.add(all4);
		allSetUser2.add(all10);
		
		Set<IngredientModel> lovedIngUser2 = new HashSet<IngredientModel>();
		lovedIngUser2.add(ing2);
		lovedIngUser2.add(ing7);
		lovedIngUser2.add(ing12);
		lovedIngUser2.add(ing17);
		lovedIngUser2.add(ing22);
		
		Set<IngredientModel> hatedIngUser2 = new HashSet<IngredientModel>();
		hatedIngUser2.add(ing16);
		hatedIngUser2.add(ing23);
		hatedIngUser2.add(ing24);

		UserModel julian = new UserModel("julian", "password", "julian@wtf.com", true, allSetUser2/*, lovedIngUser2, hatedIngUser2*/);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		userRepository.save(julian);
		
		Set<AllergieModel> allSetUser3 = new HashSet<AllergieModel>();
		allSetUser3.add(all14);
		allSetUser3.add(all13);
		
		Set<IngredientModel> lovedIngUser3 = new HashSet<IngredientModel>();
		lovedIngUser3.add(ing30);
		lovedIngUser3.add(ing31);
		lovedIngUser3.add(ing33);
		lovedIngUser3.add(ing38);
		lovedIngUser3.add(ing29);
		
		Set<IngredientModel> hatedIngUser3 = new HashSet<IngredientModel>();
		hatedIngUser3.add(ing18);
		hatedIngUser3.add(ing20);
		hatedIngUser3.add(ing21);

		UserModel lukas = new UserModel("lukas", "password", "lukas@wtf.com", true, allSetUser3/*, lovedIngUser3, hatedIngUser3*/);
		tim.encryptPassword();
		tim.addUserRole(userRole); 
		tim.addUserRole(adminRole);
		userRepository.save(lukas);
		
		Set<AllergieModel> allSetUser4 = new HashSet<AllergieModel>();
		allSetUser4.add(all7);
		allSetUser4.add(all8);
		
		Set<IngredientModel> lovedIngUser4 = new HashSet<IngredientModel>();
		lovedIngUser3.add(ing2);
		lovedIngUser3.add(ing3);
		lovedIngUser3.add(ing4);
		lovedIngUser3.add(ing5);
		lovedIngUser3.add(ing6);
		
		Set<IngredientModel> hatedIngUser4 = new HashSet<IngredientModel>();
		hatedIngUser3.add(ing30);
		hatedIngUser3.add(ing31);
		hatedIngUser3.add(ing27);

		UserModel simone = new UserModel("simone", "password", "simone@wtf.com", true, allSetUser4/*, lovedIngUser4, hatedIngUser4*/);
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
		
		
		tim.addUserIFollow(julian);
		tim.addUserIFollow(simone);
		julian.addUserIFollow(tim);
		julian.addUserIFollow(simone);
		lukas.addUserIFollow(tim);
		lukas.addUserIFollow(julian);
		lukas.addUserIFollow(simone);
		simone.addUserIFollow(tim);
		
		tim.addUserFollowingMe(julian);
		tim.addUserFollowingMe(simone);
		tim.addUserFollowingMe(lukas);
		julian.addUserFollowingMe(tim);
		julian.addUserFollowingMe(julian);
		julian.addUserFollowingMe(lukas);
		simone.addUserFollowingMe(julian);
		simone.addUserFollowingMe(tim);
		simone.addUserFollowingMe(lukas);







		// ----------------------------------------
		// -------------- CATEGORIE_MODEL
		// ----------------------------------------

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

		CategorieModel cat27 = new CategorieModel("Austrian");
		categorieRepository.save(cat27);

		CategorieModel cat28 = new CategorieModel("Stir Fry");
		categorieRepository.save(cat28);

		CategorieModel cat29 = new CategorieModel("Baking");
		categorieRepository.save(cat29);

		CategorieModel cat30 = new CategorieModel("Sauces");
		categorieRepository.save(cat30);

		// ----------------------------------------
		// -------------- RECIPE_MODEL
		// ----------------------------------------

		IngredientAmountModel IngAm1 = new IngredientAmountModel("2 cloves", ing28);
		IngredientAmountModel IngAm2 = new IngredientAmountModel("1", ing1);
		IngredientAmountModel IngAm3 = new IngredientAmountModel("2 sprigs", ing2);
		IngredientAmountModel IngAm4 = new IngredientAmountModel("6 rashers", ing3);
		IngredientAmountModel IngAm5 = new IngredientAmountModel("", ing4);
		IngredientAmountModel IngAm6 = new IngredientAmountModel("500g", ing5);
		IngredientAmountModel IngAm7 = new IngredientAmountModel("200ml", ing6);
		IngredientAmountModel IngAm8 = new IngredientAmountModel("280g", ing7);
		IngredientAmountModel IngAm9 = new IngredientAmountModel("800g", ing8);
		IngredientAmountModel IngAm10 = new IngredientAmountModel("500g", ing9);
		IngredientAmountModel IngAm11 = new IngredientAmountModel("", ing10);

		IngredientAmountModel IngAm12 = new IngredientAmountModel("", ing19);
		IngredientAmountModel IngAm13 = new IngredientAmountModel("2", ing11);
		IngredientAmountModel IngAm14 = new IngredientAmountModel("2/3 cup", ing12);
		IngredientAmountModel IngAm15 = new IngredientAmountModel("3.5 oz", ing13);
		IngredientAmountModel IngAm16 = new IngredientAmountModel("1 tsp", ing14);
		IngredientAmountModel IngAm17 = new IngredientAmountModel("5 cups", ing15);
		IngredientAmountModel IngAm18 = new IngredientAmountModel("a handfull", ing16);
		IngredientAmountModel IngAm19 = new IngredientAmountModel("", ing17);
		IngredientAmountModel IngAm20 = new IngredientAmountModel("", ing18);

		IngredientAmountModel IngAm21 = new IngredientAmountModel("1", ing1);
		IngredientAmountModel IngAm22 = new IngredientAmountModel("1", ing20);
		IngredientAmountModel IngAm23 = new IngredientAmountModel("1/2", ing21);
		IngredientAmountModel IngAm24 = new IngredientAmountModel("2", ing7);
		IngredientAmountModel IngAm25 = new IngredientAmountModel("2", ing22);
		IngredientAmountModel IngAm26 = new IngredientAmountModel("200g", ing23);
		IngredientAmountModel IngAm27 = new IngredientAmountModel("200g", ing24);
		IngredientAmountModel IngAm28 = new IngredientAmountModel("200g", ing25);
		IngredientAmountModel IngAm29 = new IngredientAmountModel("12", ing26);
		IngredientAmountModel IngAm30 = new IngredientAmountModel("350g", ing27);
		IngredientAmountModel IngAm31 = new IngredientAmountModel("2", ing28);
		IngredientAmountModel IngAm32 = new IngredientAmountModel("a pinch", ing29);
		IngredientAmountModel IngAm33 = new IngredientAmountModel("a sprig of", ing16);
		IngredientAmountModel IngAm34 = new IngredientAmountModel("", ing4);
		IngredientAmountModel IngAm35 = new IngredientAmountModel("800ml", ing30);

		IngredientAmountModel IngAm37 = new IngredientAmountModel("4", ing31);
		IngredientAmountModel IngAm38 = new IngredientAmountModel("2", ing11);
		IngredientAmountModel IngAm39 = new IngredientAmountModel("100g", ing13);
		IngredientAmountModel IngAm40 = new IngredientAmountModel("100g", ing32);
		IngredientAmountModel IngAm41 = new IngredientAmountModel("", ing19);
		IngredientAmountModel IngAm42 = new IngredientAmountModel("", ing18);
		IngredientAmountModel IngAm43 = new IngredientAmountModel("", ing33);
		IngredientAmountModel IngAm44 = new IngredientAmountModel("slices of", ing34);

		IngredientAmountModel IngAm45 = new IngredientAmountModel("1.1l", ing35);
		IngredientAmountModel IngAm46 = new IngredientAmountModel("1 large", ing1);
		IngredientAmountModel IngAm47 = new IngredientAmountModel("2 cloves of", ing28);
		IngredientAmountModel IngAm48 = new IngredientAmountModel("1/2 head of", ing36);
		IngredientAmountModel IngAm49 = new IngredientAmountModel("90g", ing10);
		IngredientAmountModel IngAm50 = new IngredientAmountModel("1 tsp", ing4);
		IngredientAmountModel IngAm51 = new IngredientAmountModel("", ing33);
		IngredientAmountModel IngAm52 = new IngredientAmountModel("400g", ing37);
		IngredientAmountModel IngAm53 = new IngredientAmountModel("2 wine glasses of", ing38);

		Set<IngredientAmountModel> ingSet1 = new HashSet<IngredientAmountModel>();
		ingSet1.add(IngAm1);
		ingSet1.add(IngAm2);
		ingSet1.add(IngAm3);
		ingSet1.add(IngAm4);
		ingSet1.add(IngAm5);
		ingSet1.add(IngAm6);
		ingSet1.add(IngAm7);
		ingSet1.add(IngAm8);
		ingSet1.add(IngAm9);
		ingSet1.add(IngAm10);
		ingSet1.add(IngAm11);

		Set<IngredientAmountModel> ingSet2 = new HashSet<IngredientAmountModel>();
		ingSet1.add(IngAm12);
		ingSet1.add(IngAm13);
		ingSet1.add(IngAm14);
		ingSet1.add(IngAm15);
		ingSet1.add(IngAm16);
		ingSet1.add(IngAm17);
		ingSet1.add(IngAm18);
		ingSet1.add(IngAm19);
		ingSet1.add(IngAm20);

		Set<IngredientAmountModel> ingSet3 = new HashSet<IngredientAmountModel>();
		ingSet1.add(IngAm21);
		ingSet1.add(IngAm22);
		ingSet1.add(IngAm23);
		ingSet1.add(IngAm24);
		ingSet1.add(IngAm25);
		ingSet1.add(IngAm26);
		ingSet1.add(IngAm27);
		ingSet1.add(IngAm28);
		ingSet1.add(IngAm29);
		ingSet1.add(IngAm30);
		ingSet1.add(IngAm31);
		ingSet1.add(IngAm32);
		ingSet1.add(IngAm33);
		ingSet1.add(IngAm34);
		ingSet1.add(IngAm35);

		Set<IngredientAmountModel> ingSet4 = new HashSet<IngredientAmountModel>();
		ingSet1.add(IngAm37);
		ingSet1.add(IngAm38);
		ingSet1.add(IngAm39);
		ingSet1.add(IngAm40);
		ingSet1.add(IngAm41);
		ingSet1.add(IngAm42);
		ingSet1.add(IngAm43);
		ingSet1.add(IngAm44);

		Set<IngredientAmountModel> ingSet5 = new HashSet<IngredientAmountModel>();
		ingSet1.add(IngAm45);
		ingSet1.add(IngAm46);
		ingSet1.add(IngAm47);
		ingSet1.add(IngAm48);
		ingSet1.add(IngAm49);
		ingSet1.add(IngAm50);
		ingSet1.add(IngAm51);
		ingSet1.add(IngAm52);
		ingSet1.add(IngAm53);

		Set<CategorieModel> catSet1 = new HashSet<CategorieModel>();
		catSet1.add(cat1);
		catSet1.add(cat7);
		catSet1.add(cat9);
		catSet1.add(cat16);
		catSet1.add(cat17);
		catSet1.add(cat26);

		Set<CategorieModel> catSet2 = new HashSet<CategorieModel>();
		catSet1.add(cat5);
		catSet1.add(cat27);

		Set<CategorieModel> catSet3 = new HashSet<CategorieModel>();
		catSet1.add(cat7);
		catSet1.add(cat11);

		Set<CategorieModel> catSet4 = new HashSet<CategorieModel>();
		catSet1.add(cat7);
		catSet1.add(cat9);
		catSet1.add(cat27);

		Set<CategorieModel> catSet5 = new HashSet<CategorieModel>();
		catSet1.add(cat1);
		catSet1.add(cat7);

		// ----------------------------------------
		// -------------- RECIPE_MODEL
		// ----------------------------------------
		Date now = new Date();

		RecipeModel rec1 = new RecipeModel(now, now, "Spaghetti Bolognese",
				"1) Preheat the oven to 180∫C/350∫F/gas 4.\n" + "\n"
						+ "2) Peel and finely chop the garlic and onions, pick and finely chop the rosemary, then finely slice the bacon.\n"
						+ "\n"
						+ "3) Heat a splash of oil in a casserole pan on a medium heat, add the bacon, rosemary, garlic and onion and cook for 5 minutes, or until softened, stirring occasionally.\n"
						+ "\n"
						+ "4) Add the minced beef, breaking it apart with the back of a spoon, then cook for 2 to 3 minutes, or until starting to brown, then pour in the wine. Leave to bubble and cook away.\n"
						+ "\n"
						+ "5) Meanwhile, drain and tip the sun-dried tomatoes into a food processor, blitz to a paste, then add to the pan with the tomatoes. Stir well, break the plum tomatoes apart a little.\n"
						+ "\n"
						+ "6) Cover with a lid then place in the oven for 1 hour, removing the lid and giving it a stir after 30 minutes ñ if it looks a little dry at this stage, add a splash of water to help it along.\n"
						+ "\n"
						+ "7) About 10 minutes before the time is up, cook the spaghetti in boiling salted water according to the packet instructions.\n"
						+ "\n"
						+ "8) Once the spaghetti is cooked, drain, reserving a mugful of cooking water, then return to the pan with a few spoons of Bolognese, a good grating of Parmesan and a drizzle of extra virgin olive oil.\n"
						+ "\n" + "9) Toss to coat the spaghetti, loosening with a splash of cooking water, if needed.\n"
						+ "\n"
						+ "10) Divide the spaghetti between plates or bowls, add a good spoonful of Bolognese to each, then serve with a fine grating of Parmesan.\n"
						+ "\n" + "",
				julian, ingSet1, true, true, catSet1);
		recipeRepository.save(rec1);

		RecipeModel rec2 = new RecipeModel(now, now, "Sliced Crepe Broth",
				"1) Sift the flour over a large bowl and then add the sugar, milk, eggs and a pinch of salt.\n" + "\n"
						+ "2) Mix the ingredients well until you end up with a very runny batter. Make sure that there are no flour lumps in it. Place a medium non-stick pan over medium-high heat until hot. Then add a tiny drop of vegetable oil and with a brush oil the pan lightly. Then add a generous spoonful of the crepe batter to the hot pan.\n"
						+ "\n"
						+ "3) Lightly turn the pan a little to spread the batter over the surface evenly. Then let the thin crepe bake for about a minute until the edges turn a little brown. Carefully lift the crepe using a spatula and see if it's golden enough. Then flip the crepe over and bake it on its uncooked side for another minute.\n"
						+ "\n"
						+ "4) Once baked, transfer the crepe to a plate and cover with some tinfoil. Add another drop of vegetable oil to the pan, brush it around and add another batch of crepe batter. Do this until there's no crepe batter left. You should have about 4 to 5 thin crepes. Then bring the broth to a gentle boil. Check the seasoning and add extra salt or pepper. Add the chopped herbs.\n"
						+ "\n"
						+ "5) Roll the crepes up together and slice them using a long and sharp knife, make thin strips. Add the crepe noodles to the hot broth and stir well.\n"
						+ "\n" + "6) Scoop the crepe soup into bowl and serve piping hot.",
				tim, ingSet2, true, true, catSet2);
		recipeRepository.save(rec2);

		RecipeModel rec3 = new RecipeModel(now, now, "Paella", "PREPARATION: \n" + "\n"
				+ "MUSSELS: Wash the mussels, removing the beards. Throw away any that donít shut on contact with water.\n"
				+ "\n"
				+ "FRESH SQUID: Rub off the outer dark skin. Pull out the insides (including the transparent back bone) and pinch the eye away from the tentacles. Save the tentacles. Cut the squid into rings.\n"
				+ "\n"
				+ "CLAMS: Wash in water and then put in a bowl with some salt so that the grit comes out. Throw away any that are open.\n"
				+ "\n"
				+ "PRAWNS: Whether you peel them or not is up to you. If you decide to peel them, save the shells and boil in water for about ten minutes. Save the liquid and add later instead of water.\n"
				+ "\n"
				+ "GARLIC: In a pestle and mortar, grind the garlic, saffron (if using), parsley and a pinch of salt.\n"
				+ "\n" + "RECIPE:\n" + "\n"
				+ "1) Heat some olive oil in a large frying pan. Add the onion, green pepper and carrot and fry gently for about five minutes. Add the chopped tomato and squid (with the tentacles) and fry on a low heat for another ten minutes.\n"
				+ "\n"
				+ "2) Add the rice and stir well to make sure that it is thoroughly coated. Add water (or the water from boiling the prawn shells or fish stock if using frozen fish), clams and the garlic/saffron/parsley mixture and bring to the boil. Season with salt. Put a lid on it, turn the heat right down and cook very slowly for about ten minutes. Add the prawns and peas and give it a stir. Arrange the mussels and strips of red pepper artistically on top, put the lid back on and leave for another ten minutes - checking that it has enough water. If you think it is getting too dry, add more water, but shake the handle of the pan rather than stir so as not to upset the pattern. Once the rice is cooked and the mussels have opened, it is ready to eat. ",
				julian, ingSet3, true, true, catSet3);
		recipeRepository.save(rec3);

		RecipeModel rec4 = new RecipeModel(now, now, "Wiener Schnitzel",
				"1) Lay out the schnitzel, remove any skin and beat until thin. Season on both sides with salt and pepper. Place flour and breadcrumbs into separate flat plates, beat the eggs together on a further plate using a fork.\n"
						+ "\n"
						+ "2) Coat each schnitzel firstly on both sides in flour, then draw through the beaten eggs, ensuring that no part of the schnitzel remains dry. Lastly, coat in the breadcrumbs and carefully press down the crumbs using the reverse side of the fork (this causes the crumb coating to ìfluff upî better during cooking).\n"
						+ "\n"
						+ "3) In a large pan (or 2 medium-sized pans), melt sufficient clarified butter for the schnitzel to be able to swim freely in the oil (or heat up the plant oil with 1 ñ 2 tbsp of clarified butter or butter).\n"
						+ "\n"
						+ "4) Only place the Schnitzel in the pan when the fat is so hot that it hisses and bubbles up if some breadcrumbs or a small piece of butter is introduced to it.\n"
						+ "\n"
						+ "5) Depending on the thickness and the type of meat, fry for between 2 minutes and 4 minutes until golden brown. Turn using a spatula (do not pierce the coating!) and fry on the other side until similarly golden brown.\n"
						+ "\n"
						+ "6) Remove the crispy schnitzel and place on kitchen paper to dry off. Dab carefully to dry the schnitzel. Arrange on the plate and garnish with slices of lemon before serving.\n"
						+ "\n" + "7) Serve with parsley potatoes, rice, potato salad or mixed salad.\n" + "\n"
						+ "Cooking time: depending on the thickness and the meat, 4 ñ 8 minutes",
				tim, ingSet4, true, true, catSet4);
		recipeRepository.save(rec4);

		RecipeModel rec5 = new RecipeModel(now, now, "Basic risotto",
				"1) Heat the stock. Peel and finely chop the onion and garlic, trim and finely chop the celery. Finely grate the Parmesan.\n"
						+ "\n"
						+ "2) In a separate pan, heat the oil and 1 small knob of butter over a low heat, add the onions, garlic and celery, and fry gently for about 15 minutes, or until softened but not coloured.\n"
						+ "\n"
						+ "3) Add the rice and turn up the heat ñ the rice will now begin to lightly fry, so keep stirring it. After 1 minute it will look slightly translucent. Add the vermouth or wine and keep stirring ó it will smell fantastic. Any harsh alcohol flavours will evaporate and leave the rice with a tasty essence.\n"
						+ "\n"
						+ "4) Once the vermouth or wine has cooked into the rice, add your first ladle of hot stock and a good pinch of sea salt. Turn the heat down to a simmer so the rice doesnít cook too quickly on the outside.\n"
						+ "\n"
						+ "5) Keep adding ladlefuls of stock, stirring and almost massaging the creamy starch out of the rice, allowing each ladleful to be absorbed before adding the next. This will take around 15 minutes. Taste the rice ó is it cooked? Carry on adding stock until the rice is soft but with a slight bite. Donít forget to check the seasoning carefully. If you run out of stock before the rice is cooked, add some boiling water.\n"
						+ "\n"
						+ "6) Remove the pan from the heat, add 1 knob of butter and the Parmesan, then stir well.\n"
						+ "\n"
						+ "7) Place a lid on the pan and allow to sit for 2 minutes ñ this is the most important part of making the perfect risotto, as this is when it becomes outrageously creamy and oozy like it should be. Eat it as soon as possible, while the risotto retains its beautiful texture.\n"
						+ "\n" + "",
				julian, ingSet5, true, true, catSet5);
		recipeRepository.save(rec5);

		// ----------------------------------------
		// -------------- COMMENT_MODEL
		// ----------------------------------------
		
		CommentModel com1 = new CommentModel("Super recepie!", now, rec1, tim);
		commentRepository.save(com1);
		CommentModel com2 = new CommentModel("It tastes really great.", now, rec1, tim);
		commentRepository.save(com2);

		CommentModel com3 = new CommentModel("I really liked it. Fun to cook!", now, rec2, tim);
		commentRepository.save(com3);
		CommentModel com4 = new CommentModel("Tasted wonderfull!", now, rec2, tim);
		commentRepository.save(com4);

		
		CommentModel com5 = new CommentModel("Great Paella, i recommend it!", now, rec3, tim);
		commentRepository.save(com5);
		CommentModel com6 = new CommentModel("Try to cook it yourself! It is easy and very good.", now, rec3, tim);
		commentRepository.save(com6);

		
		CommentModel com7 = new CommentModel("I enjoyed the food, but my wife didn't.", now, rec4, tim);
		commentRepository.save(com7);
		CommentModel com8 = new CommentModel("Do you find the errror Lukas? #GrammarNazi", now, rec4, tim);
		commentRepository.save(com8);
		
		CommentModel com9 = new CommentModel("You can use this basic recepie and add what ever you want.", now, rec5, tim);
		commentRepository.save(com9);
		CommentModel com10 = new CommentModel("If it is good enough for me, it is good enough for everyone!", now, rec5, tim);
		commentRepository.save(com10);

		// ----------------------------------------
		// -------------- RECIPIE_COLLECTION_MODEL
		// ----------------------------------------
		
		RecipeCollectionModel col1 = new RecipeCollectionModel ("Favorites", julian);
		RecipeCollectionModel col2 = new RecipeCollectionModel ("Favorites", tim);
		RecipeCollectionModel col3 = new RecipeCollectionModel ("Favorites", lukas);
		RecipeCollectionModel col4 = new RecipeCollectionModel ("Favorites", simone);

		Set<RecipeModel> colSet1 = new HashSet<RecipeModel>();
		colSet1.add(rec1);
		colSet1.add(rec4);
		colSet1.add(rec3);

		Set<RecipeModel> colSet2 = new HashSet<RecipeModel>();
		colSet2.add(rec1);
		colSet2.add(rec2);
		
		Set<RecipeModel> colSet3 = new HashSet<RecipeModel>();
		colSet3.add(rec5);

		Set<RecipeModel> colSet4 = new HashSet<RecipeModel>();
		colSet4.add(rec1);
		colSet4.add(rec2);
		colSet4.add(rec3);
		colSet4.add(rec4);
		colSet4.add(rec5);

		col1.setRecipes(colSet1);
		col2.setRecipes(colSet2);
		col3.setRecipes(colSet3);
		col4.setRecipes(colSet4);
		
		recipeCollectionRepository.save(col1);
		recipeCollectionRepository.save(col2);
		recipeCollectionRepository.save(col3);
		recipeCollectionRepository.save(col4);
		
		return "forward:login";
	}
}
