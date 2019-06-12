package at.fh.swenga.jpa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
 
@Entity
@Table(name = "recipe_collections")
public class RecipeCollectionModel implements java.io.Serializable {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel user;
	
	@ManyToMany(mappedBy = "recipeCollections", fetch = FetchType.LAZY)
	private Set<RecipeModel> recipes;

	public RecipeCollectionModel(String title, UserModel user) {
		super();
		this.title = title;
		this.user = user;
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Set<RecipeModel> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeModel> recipes) {
		this.recipes = recipes;
	}
}
