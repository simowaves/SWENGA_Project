package at.fh.swenga.jpa.model;

import java.util.Date;
import java.util.HashSet;
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
	
	public RecipeCollectionModel() {
		super();
	}

	public RecipeCollectionModel(String title) {
		super();
		this.title = title;
	}

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
	
	public void addRecipe(RecipeModel recipe) {
		if (recipes==null) recipes = new HashSet<RecipeModel>();
		recipes.add(recipe);
	}
	
	public void removeRecipe(RecipeModel recipe) {
		if (recipes==null) recipes = new HashSet<RecipeModel>();
		recipes.remove(recipe);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeCollectionModel other = (RecipeCollectionModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
