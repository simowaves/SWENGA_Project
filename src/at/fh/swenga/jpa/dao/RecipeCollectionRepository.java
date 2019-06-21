package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.RecipeCollectionModel;

@Repository
@Transactional
public interface RecipeCollectionRepository extends JpaRepository<RecipeCollectionModel, Integer> {
	
	
	@Query("SELECT DISTINCT c "
			+ "FROM RecipeCollectionModel c "
			+ "LEFT JOIN FETCH c.recipes r "
			+ "JOIN c.user u "
			+ "WHERE u.id = :id")
	public List<RecipeCollectionModel> findCollectionsByUserId(@Param("id") int id);
	
	public RecipeCollectionModel findCollectionsById(int id);
	
	
	
	@Query("SELECT c "
			+ "FROM RecipeCollectionModel c "
			+ "LEFT JOIN FETCH c.recipes r "
			+ "JOIN FETCH c.user u "
			+ "WHERE c.id = :id")
	public RecipeCollectionModel findCollectionsByIdWithRecipesAndUser(@Param("id") int id);
	
	
}
