package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.RecipeModel;

@Repository
@Transactional
public interface RecipeRepository extends JpaRepository<RecipeModel, Integer> {
		
	public RecipeModel findRecipeById (int id);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.categories c "
			+ "WHERE c.id = :catId ")
	public List<RecipeModel> findRecipesByCategorieId (@Param("catId") int catId);
	
	// filters the recipes and returns all recipes that the user isn't allergic against, or don't have hated Ingredients and it filters of categories
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.categories c "
			+ "WHERE r.id not in (SELECT x "
			+ 	"FROM RecipeModel AS x "
			+ 	"LEFT JOIN x.ingredientAmounts ia "
			+ 	"LEFT JOIN ia.ingredient i "
			+ 	"LEFT JOIN i.allergies a "
			+ 	"LEFT JOIN a.users ua "
			+ 	"LEFT JOIN i.usersHateMe iu "
			+ 	"WHERE ua.id = :userId OR iu.id = :userId " 
			+ 	"GROUP BY x.id) "
			+ "AND c.id = :catId ")
	public List<RecipeModel> filterRecipesByUserPreferencesAndCategorieId (@Param("userId") int userId, @Param("catId") int catId);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.ingredientAmounts ia "
			+ "JOIN ia.ingredient i "
			+ "WHERE i.id = :ingId ")
	public List<RecipeModel> findRecipesByIngredientId (@Param("ingId") int ingId);
	
	// filters the recipes and returns all recipes that the user isn't allergic against, or don't have hated Ingredients and it filters of ingredients
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.ingredientAmounts ia "
			+ "JOIN ia.ingredient i "
			+ "WHERE r.id not in (SELECT x "
			+ 	"FROM RecipeModel AS x "
			+ 	"LEFT JOIN x.ingredientAmounts ia "
			+ 	"LEFT JOIN ia.ingredient i "
			+ 	"LEFT JOIN i.allergies a "
			+ 	"LEFT JOIN a.users ua "
			+ 	"LEFT JOIN i.usersHateMe iu "
			+ 	"WHERE ua.id = :userId OR iu.id = :userId " 
			+ 	"GROUP BY x.id) "
			+ "AND i.id = :ingId ")
	public List<RecipeModel> filterRecipesByUserPreferencesAndIngredientId (@Param("userId") int userId, @Param("ingId") int ingId);
		
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "WHERE LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') ")
	public List<RecipeModel> findRecipesBySearchString (@Param("searchString") String searchString);
	
	// filters the recipes and returns all recipes that the user isn't allergic against, or don't have hated Ingredients
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "WHERE r.id not in (SELECT x "
			+ 	"FROM RecipeModel AS x "
			+ 	"LEFT JOIN x.ingredientAmounts ia "
			+ 	"LEFT JOIN ia.ingredient i "
			+ 	"LEFT JOIN i.allergies a "
			+ 	"LEFT JOIN a.users ua "
			+ 	"LEFT JOIN i.usersHateMe iu "
			+ 	"WHERE ua.id = :userId OR iu.id = :userId " 
			+ 	"GROUP BY x.id) "
			+ "AND LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') ")
	public List<RecipeModel> filterRecipesByUserPreferencesAndSearchString (@Param("userId") int userId, @Param("searchString") String searchString);
	
	// filters the recipes and returns all recipes that the user isn't allergic against, or don't have hated Ingredients
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "WHERE r.id not in (SELECT x "
			+ 	"FROM RecipeModel AS x "
			+ 	"LEFT JOIN x.ingredientAmounts ia "
			+ 	"LEFT JOIN ia.ingredient i "
			+ 	"LEFT JOIN i.allergies a "
			+ 	"LEFT JOIN a.users ua "
			+ 	"LEFT JOIN i.usersHateMe iu "
			+ 	"WHERE ua.id = :userId OR iu.id = :userId " 
			+ 	"GROUP BY x.id)")
	public List<RecipeModel> filterRecipesByUserPreferences (@Param("userId") int userId);
	
	// sorts all recipes with the amount of loved Ingredients from the user
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
//			+ "LEFT JOIN FETCH r.categories c "
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersLoveMe iu " 
			+ "GROUP BY r.id "
			+ "ORDER BY COUNT(CASE iu.id WHEN :userId THEN 1 ELSE NULL END) DESC")
	public List<RecipeModel> findRecipesOrderedByfavoriteIngredients (@Param("userId") int userId);
	
	// sorts all recipes with the amount of likes
		@Query ("SELECT r "
				+ "FROM RecipeModel AS r "
//				+ "LEFT JOIN FETCH r.categories c "
				+ "LEFT JOIN r.likingUsers lu "
				+ "GROUP BY r.id "
				+ "ORDER BY COUNT(lu.id) DESC")
	public List<RecipeModel> findRecipesOrderedByLikesWithCategories ();
}
