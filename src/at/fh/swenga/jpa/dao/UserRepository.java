package at.fh.swenga.jpa.dao;

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
			+ "JOIN u.reportedRecipes r "
			+ "WHERE r.id = :recId AND u.id = :userId")
	public UserModel findReportingUserFromRecipe(@Param("recId") int recId, @Param("userId") int userId);
	
	// give me the user that has reported that recipe
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "JOIN u.likedRecipes r "
			+ "WHERE r.id = :recId AND u.id = :userId")
	public UserModel findLikingUserFromRecipe(@Param("recId") int recId, @Param("userId") int userId);
	
	// give me the user with the username with its reportedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "JOIN FETCH u.reportedRecipes r "
			+ "WHERE LOWER(u.userName) = LOWER(:searchString) ")
	public UserModel findUserByUserNameWithReportedRecipes(@Param("searchString") String searchString);
		
	// give me the user with the username with its likedRecipes
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "JOIN FETCH u.likedRecipes r "
			+ "WHERE LOWER(u.userName) = LOWER(:searchString) ")
	public UserModel findUserByUserNameWithLikedRecipes(@Param("searchString") String searchString);


		
		
}
