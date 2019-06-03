package at.fh.swenga.jpa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoleModel implements java.io.Serializable {
	private static final long serialVersionUID = 8098173157518993615L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	@ManyToMany(mappedBy = "userRoles", fetch = FetchType.LAZY)
	private Set<UserModel> users;
 
	@Column(name = "role", nullable = false, length = 45)
	private String role;
 
	public UserRoleModel() {
		// TODO Auto-generated constructor stub
	}
 
	public UserRoleModel(String role) {
		super();
		this.role = role;
	}
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public Set<UserModel> getUsers() {
		return users;
	}
 
	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
}