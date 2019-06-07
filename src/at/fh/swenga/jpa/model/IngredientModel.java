package at.fh.swenga.jpa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
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
	
	@Version
	long version;
	
}
