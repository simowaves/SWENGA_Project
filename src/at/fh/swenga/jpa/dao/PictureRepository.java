package at.fh.swenga.jpa.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.PictureModel;
 
@Repository
@Transactional
public interface PictureRepository extends JpaRepository<PictureModel, Integer> {
	/*
	@Query("SELECT p "
			+ "FROM RecipeModel r "
			+ "JOIN r.picture p "
			+ "WHERE r.id = :id")
	public PictureModel findPictureByRecipeId(@Param("id") int id);
	*/
}