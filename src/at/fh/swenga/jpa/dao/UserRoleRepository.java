package at.fh.swenga.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.UserRoleModel;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {
	
	public UserRoleModel findTop1ByRole (String role);
 

}
