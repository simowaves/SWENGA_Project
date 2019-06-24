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
	
	@Query("SELECT r "
			+ "FROM RecipeModel r "
			+ "WHERE r.enabled = true "
			+ "AND r.id = :id ")
	public RecipeModel findRecipeById (@Param("id") int id);
	
	@Query("SELECT r "
			+ "FROM RecipeModel r "
			+ "LEFT JOIN FETCH r.recipeCollections c "
			+ "WHERE r.enabled = true "
			+ "AND r.id = :id ")
	public RecipeModel findRecipeByIdWithCollections (@Param("id") int id);
	
	@Query("SELECT r "
			+ "FROM RecipeModel r "
			+ "LEFT JOIN FETCH r.picture p "
			+ "WHERE r.enabled = true "
			+ "AND r.id = :id ")
	public RecipeModel findRecipeByIdWithPicture (@Param("id") int id);
	
	@Query("SELECT r "
			+ "FROM RecipeModel r "
			+ "LEFT JOIN FETCH r.picture p "
			+ "LEFT JOIN FETCH r.reportingUsers ru "
			+ "LEFT JOIN FETCH r.likingUsers lu "
			+ "WHERE r.enabled = true "
			+ "AND r.id = :id ")
	public RecipeModel findRecipeByIdWithPictureAndLikingUsersAndReportingUsers (@Param("id") int id);
	
	@Query("SELECT r "
			+ "FROM RecipeModel r "
			+ "LEFT JOIN FETCH r.reportingUsers u "
			+ "WHERE r.enabled = true "
			+ "AND r.id = :id ")
	public RecipeModel findRecipeByIdWithReportingUsers (@Param("id") int id);
	
	@Query("SELECT r "
			+ "FROM RecipeModel r "
			+ "JOIN FETCH r.ingredientAmounts ia "
			+ "WHERE r.enabled = true "
			+ "AND r.id = :id ")
	public RecipeModel findRecipeByIdWithIngredientAmounts (@Param("id") int id);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.categories c "
			+ "WHERE c.id = :catId ")
	public List<RecipeModel> findRecipesByCategorieId (@Param("catId") int catId);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.author u "
			+ "WHERE u.id = :userId "
			+ "AND r.enabled = true ")
	public List<RecipeModel> findRecipesByUserId (@Param("userId") int userId);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.author u "
			+ "WHERE u.id = :userId "
			+ "AND r.enabled = true "
			+ "AND r.published = true ")
	public List<RecipeModel> findPublishedRecipesByUserId (@Param("userId") int userId);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.likingUsers u "
			+ "WHERE u.id = :userId "
			+ "AND r.enabled = true ")
	public List<RecipeModel> findRecipesByLikingUserId (@Param("userId") int userId);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN r.ingredientAmounts ia "
			+ "JOIN ia.ingredient i "
			+ "WHERE i.id = :ingId ")
	public List<RecipeModel> findRecipesByIngredientId (@Param("ingId") int ingId);
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "WHERE LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') ")
	public List<RecipeModel> findRecipesBySearchString (@Param("searchString") String searchString);
	
	// sorts all recipes with the amount of likes
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN r.likingUsers lu "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "GROUP BY r.id "
			+ "ORDER BY COUNT(lu.id) DESC")
	public List<RecipeModel> findRecipesOrderedByLikes();
		
	// sorts all recipes with the amount of likes
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "ORDER BY r.lastEdited DESC")
	public List<RecipeModel> findRecipesOrderedByLastEdited();
		
	// give me all the users with its userRoles and comments and recipes
	@Query ("SELECT DISTINCT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN FETCH r.reportingUsers ru "
			+ "WHERE r.enabled = true ")
	public List<RecipeModel> findRecipesWithReportingUsers();
	

	public List<RecipeModel> findTop3ByOrderByTitleAsc();
	
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "JOIN FETCH r.recipeCollections c "
			+ "WHERE c.id = :colId ")
	public List<RecipeModel> findRecipesByCollectionId(@Param("colId") int colId);

	// ---------------------------------------------------------------------------- new filter Query ----------------------------------------
	
	// give me all the recipes that the user can eat
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersLoveMe uli "
			+ "LEFT JOIN i.usersHateMe uhi "
			+ "LEFT JOIN i.allergies a "
			+ "LEFT JOIN a.users ua "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "GROUP BY r "
			+ "HAVING COUNT(CASE uhi.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "AND COUNT(CASE ua.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "ORDER BY COUNT(CASE uli.id WHEN :userId THEN 1 ELSE NULL END) DESC ")
	public List<RecipeModel> findRecipesFilteredByUserPreferences(@Param("userId") int userId);
	
	// give me all the recipes that the user can eat
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersHateMe uhi "
			+ "LEFT JOIN i.allergies a "
			+ "LEFT JOIN a.users ua "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "GROUP BY r "
			+ "HAVING COUNT(CASE uhi.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "AND COUNT(CASE ua.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "ORDER BY r.lastEdited DESC ")
	public List<RecipeModel> findRecipesFilteredByUserPreferencesOrderedByLastEdited(@Param("userId") int userId);
		
	
	// give me all the recipes that the user can eat
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN r.categories c "
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersLoveMe uli "
			+ "LEFT JOIN i.usersHateMe uhi "
			+ "LEFT JOIN i.allergies a "
			+ "LEFT JOIN a.users ua "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "AND c.id = :catId "
			+ "GROUP BY r "
			+ "HAVING COUNT(CASE uhi.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "AND COUNT(CASE ua.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "ORDER BY COUNT(CASE uli.id WHEN :userId THEN 1 ELSE NULL END) DESC ")
	public List<RecipeModel> findRecipesFilteredByUserPreferencesAndCategorie(@Param("userId") int userId, @Param("catId") int catId);
	
	// give me all the recipes that the user can eat
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersLoveMe uli "
			+ "LEFT JOIN i.usersHateMe uhi "
			+ "LEFT JOIN i.allergies a "
			+ "LEFT JOIN a.users ua "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "AND LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "GROUP BY r "
			+ "HAVING COUNT(CASE uhi.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "AND COUNT(CASE ua.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "ORDER BY COUNT(CASE uli.id WHEN :userId THEN 1 ELSE NULL END) DESC ")
	public List<RecipeModel> findRecipesFilteredByUserPreferencesAndSearchString(@Param("userId") int userId, @Param("searchString") String searchString);
		
	// give me all the recipes that the user can eat
	@Query ("SELECT r "
			+ "FROM RecipeModel AS r "
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersLoveMe uli "
			+ "LEFT JOIN i.usersHateMe uhi "
			+ "LEFT JOIN i.allergies a "
			+ "LEFT JOIN a.users ua "
			+ "WHERE r.enabled = true "
			+ "AND r.published = true "
			+ "AND i.id = :ingId "
			+ "GROUP BY r "
			+ "HAVING COUNT(CASE uhi.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "AND COUNT(CASE ua.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
			+ "ORDER BY COUNT(CASE uli.id WHEN :userId THEN 1 ELSE NULL END) DESC ")
	public List<RecipeModel> findRecipesFilteredByUserPreferencesAndIngredient(@Param("userId") int userId, @Param("ingId") int ingId);
	
	// give me all the recipes from users that the user is following
	 @Query ("SELECT r "
	   + "FROM RecipeModel AS r "
	   + "LEFT JOIN r.ingredientAmounts ia "
	   + "LEFT JOIN ia.ingredient i "
	   + "LEFT JOIN i.usersHateMe uhi "
	   + "LEFT JOIN i.allergies a "
	   + "LEFT JOIN a.users ua "
	   + "LEFT JOIN r.author au "
	   + "LEFT JOIN au.usersFollowingMe ufm "
	   + "WHERE r.enabled = true "
	   + "AND r.published = true "
	   + "AND ufm.id = :userId "
	   + "GROUP BY r "
	   + "HAVING COUNT(CASE uhi.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
	   + "AND COUNT(CASE ua.id WHEN :userId THEN 1 ELSE NULL END) = 0 "
	   + "ORDER BY r.lastEdited DESC ")
	 public List<RecipeModel> findRecipesFilteredByUserPreferencesAndFollowedByUserOrderedByLastEdited(@Param("userId") int userId);
	
}
