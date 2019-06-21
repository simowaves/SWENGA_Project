package at.fh.swenga.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.UserRoleModel;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {
	
	@Query ("SELECT ur "
			+ "FROM UserRoleModel AS ur "
			+ "WHERE LOWER(ur.role) = LOWER(:searchString) ")
	public UserRoleModel findUserRoleByRole(@Param("searchString") String searchString);
 

}
