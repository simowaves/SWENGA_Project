package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "WHERE LOWER(u.userName) = LOWER(:searchString) ")
	public UserModel findUserByUserName(@Param("searchString") String searchString);
	
	public UserModel findUserById(int id);
	
	// give me the user that has reported that recipe
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN u.reportedRecipes r "
			+ "WHERE r.id = :recId AND u.id = :userId")
	public UserModel findReportingUserFromRecipe(@Param("recId") int recId, @Param("userId") int userId);
	
	// give me the user that has reported that recipe
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN u.likedRecipes r "
			+ "WHERE r.id = :recId AND u.id = :userId")
	public UserModel findLikingUserFromRecipe(@Param("recId") int recId, @Param("userId") int userId);
	
	// give me the user with the username with its reportedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.reportedRecipes r "
			+ "WHERE LOWER(u.userName) = LOWER(:searchString) ")
	public UserModel findUserByUserNameWithReportedRecipes(@Param("searchString") String searchString);
		
	// give me the user with the username with its likedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.likedRecipes r "
			+ "WHERE LOWER(u.userName) = LOWER(:searchString) ")
	public UserModel findUserByUserNameWithLikedRecipes(@Param("searchString") String searchString);
	
	
	// give me the user with the id with its recipes and likedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.likedRecipes lr "
			+ "LEFT JOIN FETCH u.recipes r "
			+ "WHERE u.id = :userId ")
	public UserModel findUserByIdWithRecipesAndLikedRecipes(@Param("userId") int userId);
		
	// give me the user with the username with its recipes and likedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.likedRecipes lr "
			+ "LEFT JOIN FETCH u.recipes r "
			+ "WHERE LOWER(u.userName) = LOWER(:userName) ")
	public UserModel findUserByIdWithRecipesAndLikedRecipes(@Param("userName") String userName);
	
	// give me the user with the username with its recipes and likedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.usersIFollow uif "
			+ "WHERE LOWER(u.userName) = LOWER(:userName) ")
	public UserModel findUserByUserNameWithUsersIFollow(@Param("userName") String userName);
	
	// give me the user that has reported that recipe
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN u.usersIFollow uif "
			+ "WHERE uif.id = :otherUserId AND u.id = :userId")
	public UserModel findUserIFollowFromUser(@Param("otherUserId") int recId, @Param("userId") int userId);
	
	// give me the user with the username with its allergies and lovedIngredients and hatedIngredients
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.allergies a "
			+ "LEFT JOIN FETCH u.lovedIngredients li "
			+ "LEFT JOIN FETCH u.hatedIngredients hi "
			+ "WHERE LOWER(u.userName) = LOWER(:userName) ")
	public UserModel findUserByUserNameWithAllergiesAndLovedIngredientsAndHatedIngredients(@Param("userName") String userName);
	
	// give me the user with the username with its allergies
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.allergies a "
			+ "WHERE LOWER(u.userName) = LOWER(:userName) ")
	public UserModel findUserByUserNameWithAllergies(@Param("userName") String userName);
	
	// give me the user with the username with its lovedIngredients
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.lovedIngredients li "
			+ "WHERE LOWER(u.userName) = LOWER(:userName) ")
	public UserModel findUserByUserNameWithLovedIngredients(@Param("userName") String userName);
	
	// give me the user with the username with its hatedIngredients
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.hatedIngredients hi "
			+ "WHERE LOWER(u.userName) = LOWER(:userName) ")
	public UserModel findUserByUserNameWithHatedIngredients(@Param("userName") String userName);
	
	// give me the user with the username with its hatedIngredients
	@Query ("SELECT DISTINCT u "
			+ "FROM UserModel AS u "
			+ "LEFT JOIN FETCH u.userRoles ur "
			+ "LEFT JOIN FETCH u.comments c "
			+ "LEFT JOIN FETCH u.recipes r ")
	public List<UserModel> findUsersWithUserRolesAndRecipesAndComments();
		
	
}
