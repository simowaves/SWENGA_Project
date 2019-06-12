package at.fh.swenga.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ingredients")

@NamedQueries({
})
public class IngredientModel implements java.io.Serializable{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(length = 250)
	private String description;
	
	@Column(nullable = false)
	private boolean vegan;
	
	@Column(nullable = false)
	private boolean vegetarian;

	@ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
	private Set<RecipeModel> recipes;
	
	@OneToMany(mappedBy="incredient",fetch=FetchType.LAZY)
    private Set<IngredientAmountModel> ingredientAmounts;
	
	@ManyToMany(mappedBy = "lovedIngredients", fetch = FetchType.LAZY)
	private Set<UserModel> usersLoveMe;
	
	@ManyToMany(mappedBy = "hatedIngredients", fetch = FetchType.LAZY)
	private Set<UserModel> usersHateMe;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<AllergieModel> allergies;
	
	@Version
	long version;

	public IngredientModel(String name, String description, boolean vegan, boolean vegetarian,
			Set<AllergieModel> allergies) {
		super();
		this.name = name;
		this.description = description;
		this.vegan = vegan;
		this.vegetarian = vegetarian;
		this.allergies = allergies;
	}
	
	public IngredientModel(String name, String description, boolean vegan, boolean vegetarian) {
		super();
		this.name = name;
		this.description = description;
		this.vegan = vegan;
		this.vegetarian = vegetarian;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Set<RecipeModel> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeModel> recipes) {
		this.recipes = recipes;
	}

	public Set<UserModel> getUsersLoveMe() {
		return usersLoveMe;
	}

	public void setUsersLoveMe(Set<UserModel> usersLoveMe) {
		this.usersLoveMe = usersLoveMe;
	}

	public Set<UserModel> getUsersHateMe() {
		return usersHateMe;
	}

	public void setUsersHateMe(Set<UserModel> usersHateMe) {
		this.usersHateMe = usersHateMe;
	}

	public Set<AllergieModel> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<AllergieModel> allergies) {
		this.allergies = allergies;
	}

}
