package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.IngredientModel;
import at.fh.swenga.jpa.model.RecipeModel;

@Repository
@Transactional
public interface IngredientRepository extends JpaRepository<IngredientModel, Integer> {
	
	public IngredientModel findAllByName(String name);

}
