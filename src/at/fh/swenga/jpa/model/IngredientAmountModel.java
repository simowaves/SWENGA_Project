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
@Table(name = "ingredientAmounts")

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

}
