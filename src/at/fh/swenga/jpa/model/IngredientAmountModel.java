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

	public IngredientAmountModel(String amount, IngredientModel ingredient) {
		super();
		this.amount = amount;
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

}
