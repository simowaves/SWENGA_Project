package at.fh.swenga.jpa.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.jpa.dao.AllergieRepository;
import at.fh.swenga.jpa.dao.CategorieRepository;
import at.fh.swenga.jpa.dao.CommentRepository;
import at.fh.swenga.jpa.dao.IngredientAmountRepository;
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
	IngredientAmountRepository ingredientAmountRepository; 

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

		UserRoleModel adminRole = userRoleRepository.findUserRoleByRole("ROLE_ADMIN");
		if (adminRole == null)
			adminRole = new UserRoleModel("ROLE_ADMIN");

		UserRoleModel userRole = userRoleRepository.findUserRoleByRole("ROLE_USER");
		if (userRole == null)
			userRole = new UserRoleModel("ROLE_USER");

		UserRoleModel godRole = userRoleRepository.findUserRoleByRole("ROLE_GOD");
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

		IngredientModel ing9 = new IngredientModel("Spaghetti", true, false/*, allSet1*/);
		ingredientRepository.save(ing9);
		
		all4.addIngredient(ing9);
		allergieRepository.save(all4);
			
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
		
		Set<IngredientModel> lovedIngUser1 = new HashSet<IngredientModel>();
		lovedIngUser1.add(ing1);
		lovedIngUser1.add(ing5);
		lovedIngUser1.add(ing10);
		lovedIngUser1.add(ing15);
		lovedIngUser1.add(ing20);
		lovedIngUser1.add(ing25);
		lovedIngUser1.add(ing30);
		lovedIngUser1.add(ing35);
		
		Set<IngredientModel> hatedIngUser1 = new HashSet<IngredientModel>();
		hatedIngUser1.add(ing8);
		hatedIngUser1.add(ing27);
		hatedIngUser1.add(ing38);

		UserModel tim = new UserModel("tim", "password", "tim@wtf.com", true, allSetUser1);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		tim.addUserRole(godRole);
		
		tim.addLovedIngredient(ing1);
		tim.addLovedIngredient(ing5);
		tim.addLovedIngredient(ing10);
		tim.addLovedIngredient(ing15);
		tim.addLovedIngredient(ing20);
		tim.addLovedIngredient(ing25);
		tim.addHatedIngredient(ing38);
		tim.addHatedIngredient(ing8);
		tim.addHatedIngredient(ing27);
		userRepository.save(tim);
		
		
		Set<AllergieModel> allSetUser2 = new HashSet<AllergieModel>();
		allSetUser2.add(all4);
		allSetUser2.add(all10);
		
		Set<IngredientModel> hatedIngUser2 = new HashSet<IngredientModel>();
		hatedIngUser2.add(ing16);
		hatedIngUser2.add(ing23);
		hatedIngUser2.add(ing24);

		UserModel julian = new UserModel("julian", "password", "julian@wtf.com", true, allSetUser2);
		julian.encryptPassword();
		julian.addUserRole(userRole);
		julian.addUserRole(adminRole);
		
		julian.addLovedIngredient(ing2);
		julian.addLovedIngredient(ing7);
		julian.addLovedIngredient(ing12);
		julian.addLovedIngredient(ing17);
		julian.addLovedIngredient(ing22);
		julian.addHatedIngredient(ing16);
		julian.addHatedIngredient(ing23);
		julian.addHatedIngredient(ing24);
		userRepository.save(julian);
		
		Set<AllergieModel> allSetUser3 = new HashSet<AllergieModel>();
		allSetUser3.add(all14);
		allSetUser3.add(all13);

		UserModel lukas = new UserModel("lukas", "password", "lukas@wtf.com", true, allSetUser3);
		lukas.encryptPassword();
		lukas.addUserRole(userRole); 
		lukas.addUserRole(adminRole);
		
		lukas.addLovedIngredient(ing30);
		lukas.addLovedIngredient(ing31);
		lukas.addLovedIngredient(ing33);
		lukas.addLovedIngredient(ing38);
		lukas.addLovedIngredient(ing29);
		lukas.addHatedIngredient(ing18);
		lukas.addHatedIngredient(ing20);
		lukas.addHatedIngredient(ing21);
		userRepository.save(lukas);
		
		Set<AllergieModel> allSetUser4 = new HashSet<AllergieModel>();
		allSetUser4.add(all7);
		allSetUser4.add(all8);

		UserModel simone = new UserModel("simone", "password", "simone@wtf.com", true, allSetUser4);
		simone.encryptPassword();
		simone.addUserRole(userRole);
		simone.addUserRole(adminRole);
		
		simone.addLovedIngredient(ing2);
		simone.addLovedIngredient(ing3);
		simone.addLovedIngredient(ing4);
		simone.addLovedIngredient(ing5);
		simone.addLovedIngredient(ing6);
		simone.addHatedIngredient(ing30);
		simone.addHatedIngredient(ing31);
		simone.addHatedIngredient(ing27);
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
		Date now = new Date();
		
		Set<CategorieModel> catSet1 = new HashSet<CategorieModel>();
		catSet1.add(cat1);
		catSet1.add(cat7);
		catSet1.add(cat9);
		catSet1.add(cat16);
		catSet1.add(cat17);
		catSet1.add(cat26);


		Set<CategorieModel> catSet2 = new HashSet<CategorieModel>();
		catSet2.add(cat5);
		catSet2.add(cat27);

		Set<CategorieModel> catSet3 = new HashSet<CategorieModel>();
		catSet3.add(cat7);
		catSet3.add(cat11);

		Set<CategorieModel> catSet4 = new HashSet<CategorieModel>();
		catSet4.add(cat7);
		catSet4.add(cat9);
		catSet4.add(cat27);

		Set<CategorieModel> catSet5 = new HashSet<CategorieModel>();
		catSet5.add(cat1);
		catSet5.add(cat7);
		
		Set<CategorieModel> catSet6 = new HashSet<CategorieModel>();
		catSet6.add(cat1);

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
				julian, true, true, catSet1);
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
				tim, true, true, catSet2);
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
				julian, true, true, catSet3);
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
				tim, true, true, catSet4);
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
				julian, true, true, catSet5);
		recipeRepository.save(rec5);
		
		RecipeModel rec6 = new RecipeModel(now, now, "Penis", "Penis Penis Penis Penis", lukas, true, true, catSet6);
		recipeRepository.save(rec6);

		// ----------------------------------------
		// -------------- RECIPE_MODEL
		// ----------------------------------------

		IngredientAmountModel ingAm1 = new IngredientAmountModel("2 cloves", rec1, ing28);
		ingredientAmountRepository.save(ingAm1);
		IngredientAmountModel ingAm2 = new IngredientAmountModel("1", rec1, ing1);
		ingredientAmountRepository.save(ingAm2);
		IngredientAmountModel ingAm3 = new IngredientAmountModel("2 sprigs", rec1, ing2);
		ingredientAmountRepository.save(ingAm3);
		IngredientAmountModel ingAm4 = new IngredientAmountModel("6 rashers", rec1, ing3);
		ingredientAmountRepository.save(ingAm4);
		IngredientAmountModel ingAm5 = new IngredientAmountModel("", rec1, ing4);
		ingredientAmountRepository.save(ingAm5);
		IngredientAmountModel ingAm6 = new IngredientAmountModel("500g", rec1, ing5);
		ingredientAmountRepository.save(ingAm6);
		IngredientAmountModel ingAm7 = new IngredientAmountModel("200ml", rec1, ing6);
		ingredientAmountRepository.save(ingAm7);
		IngredientAmountModel ingAm8 = new IngredientAmountModel("280g", rec1, ing7);
		ingredientAmountRepository.save(ingAm8);
		IngredientAmountModel ingAm9 = new IngredientAmountModel("800g", rec1, ing8);
		ingredientAmountRepository.save(ingAm9);
		IngredientAmountModel ingAm10 = new IngredientAmountModel("500g", rec1, ing9);
		ingredientAmountRepository.save(ingAm10);
		IngredientAmountModel ingAm11 = new IngredientAmountModel("", rec1, ing10);
		ingredientAmountRepository.save(ingAm11);

		IngredientAmountModel ingAm12 = new IngredientAmountModel("", rec2, ing19);
		ingredientAmountRepository.save(ingAm12);
		IngredientAmountModel ingAm13 = new IngredientAmountModel("2", rec2, ing11);
		ingredientAmountRepository.save(ingAm13);
		IngredientAmountModel ingAm14 = new IngredientAmountModel("2/3 cup", rec2, ing12);
		ingredientAmountRepository.save(ingAm14);
		IngredientAmountModel ingAm15 = new IngredientAmountModel("3.5 oz", rec2, ing13);
		ingredientAmountRepository.save(ingAm15);
		IngredientAmountModel ingAm16 = new IngredientAmountModel("1 tsp", rec2, ing14);
		ingredientAmountRepository.save(ingAm16);
		IngredientAmountModel ingAm17 = new IngredientAmountModel("5 cups", rec2, ing15);
		ingredientAmountRepository.save(ingAm17);
		IngredientAmountModel ingAm18 = new IngredientAmountModel("a handfull", rec2, ing16);
		ingredientAmountRepository.save(ingAm18);
		IngredientAmountModel ingAm19 = new IngredientAmountModel("", rec2, ing17);
		ingredientAmountRepository.save(ingAm19);
		IngredientAmountModel ingAm20 = new IngredientAmountModel("", rec2, ing18);
		ingredientAmountRepository.save(ingAm20);

		IngredientAmountModel ingAm21 = new IngredientAmountModel("1", rec3, ing1);
		ingredientAmountRepository.save(ingAm21);
		IngredientAmountModel ingAm22 = new IngredientAmountModel("1", rec3, ing20);
		ingredientAmountRepository.save(ingAm22);
		IngredientAmountModel ingAm23 = new IngredientAmountModel("1/2", rec3, ing21);
		ingredientAmountRepository.save(ingAm23);
		IngredientAmountModel ingAm24 = new IngredientAmountModel("2", rec3, ing7);
		ingredientAmountRepository.save(ingAm24);
		IngredientAmountModel ingAm25 = new IngredientAmountModel("2", rec3, ing22);
		ingredientAmountRepository.save(ingAm25);
		IngredientAmountModel ingAm26 = new IngredientAmountModel("200g", rec3, ing23);
		ingredientAmountRepository.save(ingAm26);
		IngredientAmountModel ingAm27 = new IngredientAmountModel("200g", rec3, ing24);
		ingredientAmountRepository.save(ingAm27);
		IngredientAmountModel ingAm28 = new IngredientAmountModel("200g", rec3, ing25);
		ingredientAmountRepository.save(ingAm28);
		IngredientAmountModel ingAm29 = new IngredientAmountModel("12", rec3, ing26);
		ingredientAmountRepository.save(ingAm29);
		IngredientAmountModel ingAm30 = new IngredientAmountModel("350g", rec3, ing27);
		ingredientAmountRepository.save(ingAm30);
		IngredientAmountModel ingAm31 = new IngredientAmountModel("2", rec3, ing28);
		ingredientAmountRepository.save(ingAm31);
		IngredientAmountModel ingAm32 = new IngredientAmountModel("a pinch", rec3, ing29);
		ingredientAmountRepository.save(ingAm32);
		IngredientAmountModel ingAm33 = new IngredientAmountModel("a sprig of", rec3, ing16);
		ingredientAmountRepository.save(ingAm33);
		IngredientAmountModel ingAm34 = new IngredientAmountModel("", rec3, ing4);
		ingredientAmountRepository.save(ingAm34);
		IngredientAmountModel ingAm35 = new IngredientAmountModel("800ml", rec3, ing30);
		ingredientAmountRepository.save(ingAm35);

		IngredientAmountModel ingAm37 = new IngredientAmountModel("4", rec4, ing31);
		ingredientAmountRepository.save(ingAm37);
		IngredientAmountModel ingAm38 = new IngredientAmountModel("2", rec4, ing11);
		ingredientAmountRepository.save(ingAm38);
		IngredientAmountModel ingAm39 = new IngredientAmountModel("100g", rec4, ing13);
		ingredientAmountRepository.save(ingAm39);
		IngredientAmountModel ingAm40 = new IngredientAmountModel("100g", rec4, ing32);
		ingredientAmountRepository.save(ingAm40);
		IngredientAmountModel ingAm41 = new IngredientAmountModel("", rec4, ing19);
		ingredientAmountRepository.save(ingAm41);
		IngredientAmountModel ingAm42 = new IngredientAmountModel("", rec4, ing18);
		ingredientAmountRepository.save(ingAm42);
		IngredientAmountModel ingAm43 = new IngredientAmountModel("", rec4, ing33);
		ingredientAmountRepository.save(ingAm43);
		IngredientAmountModel ingAm44 = new IngredientAmountModel("slices of", rec4, ing34);
		ingredientAmountRepository.save(ingAm44);

		IngredientAmountModel ingAm45 = new IngredientAmountModel("1.1l", rec5, ing35);
		ingredientAmountRepository.save(ingAm45);
		IngredientAmountModel ingAm46 = new IngredientAmountModel("1 large", rec5, ing1);
		ingredientAmountRepository.save(ingAm46);
		IngredientAmountModel ingAm47 = new IngredientAmountModel("2 cloves of", rec5, ing28);
		ingredientAmountRepository.save(ingAm47);
		IngredientAmountModel ingAm48 = new IngredientAmountModel("1/2 head of", rec5, ing36);
		ingredientAmountRepository.save(ingAm48);
		IngredientAmountModel ingAm49 = new IngredientAmountModel("90g", rec5, ing10);
		ingredientAmountRepository.save(ingAm49);
		IngredientAmountModel ingAm50 = new IngredientAmountModel("1 tsp", rec5, ing4);
		ingredientAmountRepository.save(ingAm50);
		IngredientAmountModel ingAm51 = new IngredientAmountModel("", rec5, ing33);
		ingredientAmountRepository.save(ingAm51);
		IngredientAmountModel ingAm52 = new IngredientAmountModel("400g", rec5, ing37);
		ingredientAmountRepository.save(ingAm52);
		IngredientAmountModel ingAm53 = new IngredientAmountModel("2 wine glasses of", rec5, ing38);
		ingredientAmountRepository.save(ingAm53);
		
		IngredientAmountModel ingAm54 = new IngredientAmountModel("2  glasses of", rec6, ing38);
		ingredientAmountRepository.save(ingAm54);
		IngredientAmountModel ingAm55 = new IngredientAmountModel("", rec6, ing33);
		ingredientAmountRepository.save(ingAm55);

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
		
		// ----------------------------------------
		// -------------- LIKED_RECIPE & REPORTED RECIPE
		// ----------------------------------------
		
		tim.addLikedRecipe(rec1);
		tim.addLikedRecipe(rec5);
		julian.addLikedRecipe(rec2);
		simone.addLikedRecipe(rec2);
		simone.addLikedRecipe(rec4);
		lukas.addLikedRecipe(rec5);
		
		julian.addReportedRecipe(rec6);
		tim.addReportedRecipe(rec6);
		simone.addReportedRecipe(rec6);
		
		
		return "forward:recipeList";
	}
}
