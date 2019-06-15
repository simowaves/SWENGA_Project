package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	@Query ("SELECT u "
			+ "FROM UserModel AS u "
			+ "WHERE LOWER(u.userName) = LOWER(:searchString) ")
	public UserModel findUserByUserName(@Param("searchString") String searchString);
	
	public UserModel findUserById(int id);

}
