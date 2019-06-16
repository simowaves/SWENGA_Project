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
			+ "WHERE c.id = :id ")
	public List<RecipeModel> findRecipesByCategorieId (@Param("id") int id);
	
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
			+ "LEFT JOIN r.ingredientAmounts ia "
			+ "LEFT JOIN ia.ingredient i "
			+ "LEFT JOIN i.usersLoveMe iu " 
			+ "GROUP BY r.id "
			+ "ORDER BY COUNT(CASE iu.id WHEN :userId THEN 1 ELSE NULL END) DESC")
	public List<RecipeModel> findRecipesOrderedByfavoriteIngredients (@Param("userId") int userId);
}
