package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.AllergieModel;

@Repository
@Transactional
public interface AllergieRepository extends JpaRepository<AllergieModel, Integer> {
	
	@Query ("SELECT a "
			+ "FROM AllergieModel AS a "
			+ "ORDER BY a.title")
	public List<AllergieModel> findAllAllergiesOrderByName();

	public AllergieModel findAllergieById (int id);

}
