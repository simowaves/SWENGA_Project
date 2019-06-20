package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.RecipeModel;

@Repository
@Transactional
public interface IngredientRepository extends JpaRepository<IngredientModel, Integer> {
	
	public IngredientModel findAllByName(String name);
	
	@Query ("SELECT i "
			+ "FROM IngredientModel AS i "
			+ "ORDER BY i.name ")
	public List<IngredientModel> findAllIngredientsOrderByName();
	
	public IngredientModel findIngredientById(int id);
	
	public List<IngredientModel> findTop3ByOrderById();

}
