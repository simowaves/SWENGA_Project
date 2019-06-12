package at.fh.swenga.jpa.model;

import java.util.Date;
 
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
 
@Entity
@Table(name = "allergie")
public class CommentModel implements java.io.Serializable {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 150)
	private String comment;
	
	//TODO: add time
	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createDate;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private RecipeModel recipe;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel author;

	public CommentModel(String comment, Date createDate, RecipeModel recipe, UserModel author) {
		super();
		this.comment = comment;
		this.createDate = createDate;
		this.recipe = recipe;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public RecipeModel getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeModel recipe) {
		this.recipe = recipe;
	}

	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}
 
	
}
