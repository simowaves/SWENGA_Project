package at.fh.swenga.jpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "comments")
public class CommentModel implements java.io.Serializable {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 150)
	private String content;
	
	//TODO: add time
	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createDate;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private RecipeModel recipe;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel author;

	public CommentModel() {
		super();
	}
	
	public CommentModel(String content, Date createDate) {
		super();
		this.content = content;
		this.createDate = createDate;
	}

	public CommentModel(String content, Date createDate, RecipeModel recipe, UserModel author) {
		super();
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
