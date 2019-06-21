package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.CommentModel;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
	
	
	@Query ("SELECT c "
			+ "FROM CommentModel AS c "
			+ "JOIN c.recipe r "
			+ "JOIN c.author u "
			+ "WHERE r.id = :recId "
			+ "AND u.enabled = true "
			+ "ORDER BY c.createDate")
	public List<CommentModel> findCommentsByRecipeId(@Param("recId") int recId);	

}
