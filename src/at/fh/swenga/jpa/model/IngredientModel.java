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
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="ingredient",fetch=FetchType.LAZY)
    private Set<IngredientAmountModel> ingredientAmounts;
	
	@ManyToMany(mappedBy = "lovedIngredients", fetch = FetchType.LAZY)
	private Set<UserModel> usersLoveMe;
	
	@ManyToMany(mappedBy = "hatedIngredients", fetch = FetchType.LAZY)
	private Set<UserModel> usersHateMe;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<AllergieModel> allergies;
	
	@Version
	long version;

	public IngredientModel() {
		super();
	}

	public IngredientModel(String name, String description, boolean vegetarian, boolean vegan,
			Set<AllergieModel> allergies) {
		super();
		this.name = name;
		this.description = description;
		this.vegan = vegan;
		this.vegetarian = vegetarian;
		this.allergies = allergies;
	}
	
	public IngredientModel(String name, String description, boolean vegetarian, boolean vegan) {
		super();
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.vegan = vegan;
	}
	
	public IngredientModel(String name, boolean vegetarian, boolean vegan) {
		super();
		this.name = name;
		this.vegan = vegan;
		this.vegetarian = vegetarian;
	}
	
	public IngredientModel(String name, boolean vegetarian, boolean vegan, Set<AllergieModel> allergies) {
		super();
		this.name = name;
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

	public Set<UserModel> getUsersLoveMe() {
		return usersLoveMe;
	}

	public void setUsersLoveMe(Set<UserModel> usersLoveMe) {
		this.usersLoveMe = usersLoveMe;
	}
	
	public void addUserLoveMe(UserModel userLoveMe) {
		if (usersLoveMe==null) usersLoveMe = new HashSet<UserModel>();
		usersLoveMe.add(userLoveMe);
	}

	public Set<UserModel> getUsersHateMe() {
		return usersHateMe;
	}

	public void setUsersHateMe(Set<UserModel> usersHateMe) {
		this.usersHateMe = usersHateMe;
	}
	
	public void addUserHateMe(UserModel userHateMe) {
		if (usersHateMe==null) usersHateMe = new HashSet<UserModel>();
		usersHateMe.add(userHateMe);
	}

	public Set<AllergieModel> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<AllergieModel> allergies) {
		this.allergies = allergies;
	}

	public void addAllergie(AllergieModel allergie) {
		if (allergies==null) allergies = new HashSet<AllergieModel>();
		allergies.add(allergie);
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
		IngredientModel other = (IngredientModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
