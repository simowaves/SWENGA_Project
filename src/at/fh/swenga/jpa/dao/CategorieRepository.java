package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.CategorieModel;

@Repository
@Transactional
public interface CategorieRepository extends JpaRepository<CategorieModel, Integer> {
	
	@Query ("SELECT c "
			+ "FROM CategorieModel AS c "
			+ "ORDER BY c.title ")
	public List<CategorieModel> findAllCategoriesOrderByName();
	
	public CategorieModel findCategorieById (int id);
 
	public List<CategorieModel> findTop3ByOrderByTitleAsc();
}
