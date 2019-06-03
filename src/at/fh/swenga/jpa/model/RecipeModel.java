package at.fh.swenga.jpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Recipe")


@NamedQueries({
})
public class RecipeModel implements java.io.Serializable {


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//TODO: add time
	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createDate;
	
	@Column(nullable = false, length = 30)
	private String title;
	
	@Column(nullable = false, length = 500)
	private String description;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel author;
	
	//@OneToMany (cascade = CascadeType.PERSIST)
	//private IngredientsModel ingredients;
	
	// TODO: Generate setter/getter Constructor
	
	public RecipeModel() {
	}
	
}
