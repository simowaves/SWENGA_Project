package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.CommentModel;
import at.fh.swenga.jpa.model.IngredientAmountModel;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
	
	
	@Query ("SELECT c "
			+ "FROM CommentModel AS c "
			+ "JOIN c.recipe r "
			+ "WHERE r.id = :recId "
			+ "ORDER BY c.createDate")
	public List<CommentModel> findCommentByRecipeId(@Param("recId") int recId);	

}
