package at.fh.swenga.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
 
@Entity
@Table(name = "categories")
public class CategorieModel implements java.io.Serializable {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title; 
	
	@OneToOne(cascade = CascadeType.ALL)
	private PictureModel picture;
	
	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private Set<RecipeModel> recipes;
	
	@Version
	long version;

	public CategorieModel() {
		super();
	}

	public CategorieModel(String title) {
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

	public PictureModel getPicture() {
		return picture;
	}

	public void setPicture(PictureModel picture) {
		this.picture = picture;
	}

	public Set<RecipeModel> getRecipe() {
		return recipes;
	}

	public void setRecipe(Set<RecipeModel> recipes) {
		this.recipes = recipes;
	}
	
	public void addRecipe(RecipeModel recipe) {
		if (recipes==null) recipes = new HashSet<RecipeModel>();
		recipes.add(recipe);
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
		CategorieModel other = (CategorieModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
