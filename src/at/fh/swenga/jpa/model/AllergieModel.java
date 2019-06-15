package at.fh.swenga.jpa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
 
@Entity
@Table(name = "allergies")
public class AllergieModel implements java.io.Serializable {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title;
 
	@ManyToMany(mappedBy = "allergies", fetch = FetchType.LAZY)
	private Set<IngredientModel> ingredients;
	
	@ManyToMany(mappedBy = "allergies", fetch = FetchType.LAZY)
	private Set<UserModel> users;
	
	@Version
	long version;

	public AllergieModel() {
		super();
	}

	public AllergieModel(String title) {
		super();
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<IngredientModel> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientModel> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredient(IngredientModel ingredient) {
		if (ingredients==null) ingredients = new HashSet<IngredientModel>();
		ingredients.add(ingredient);
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}
	
	public void addUser(UserModel user) {
		if (users==null) users = new HashSet<UserModel>();
		users.add(user);
	}
}
