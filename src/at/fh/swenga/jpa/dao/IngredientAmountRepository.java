package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.IngredientAmountModel;

@Repository
@Transactional
public interface IngredientAmountRepository extends JpaRepository<IngredientAmountModel, Integer> {
	
	@Query ("SELECT ia "
			+ "FROM IngredientAmountModel AS ia "
			+ "JOIN ia.recipe r "
			+ "WHERE r.id = :recId "
			+ "ORDER BY ia.id")
	public List<IngredientAmountModel> findIngredientAmountsByRecipeId(@Param("recId") int recId);
 
	public IngredientAmountModel findIngredientAmountsById (int id);
	
	

}
