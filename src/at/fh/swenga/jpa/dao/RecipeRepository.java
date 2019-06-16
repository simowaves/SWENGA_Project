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
	
	/*
	@Query ("SELECT v "
			+ "FROM VideoModel AS v "
			+ "JOIN UploaderModel AS u ON v.uploader = u.id "
			+ "WHERE LOWER(v.title) = LOWER(:searchString) "
			+ "OR LOWER(u.name) = LOWER(:searchString)")
	public List<VideoModel> findByTitleOrUploaderName(@Param("searchString") String serachString);
	*/
}
