package at.fh.swenga.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ingredient_amounts")

@NamedQueries({
})
public class IngredientAmountModel implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String amount;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private RecipeModel recipe;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private IngredientModel ingredient;
	
	@Version
	long version;

	public IngredientAmountModel() {
		super();
	}
	
	public IngredientAmountModel(String amount) {
		super();
		this.amount = amount;
	}

	public IngredientAmountModel(String amount, IngredientModel ingredient) {
		super();
		this.amount = amount;
		this.ingredient = ingredient;
	}
	
	public IngredientAmountModel(String amount, RecipeModel recipe, IngredientModel ingredient) {
		super();
		this.amount = amount;
		this.recipe = recipe;
		this.ingredient = ingredient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public RecipeModel getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeModel recipe) {
		this.recipe = recipe;
	}

	public IngredientModel getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientModel ingredient) {
		this.ingredient = ingredient;
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
		IngredientAmountModel other = (IngredientAmountModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
