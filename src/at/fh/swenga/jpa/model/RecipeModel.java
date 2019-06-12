package at.fh.swenga.jpa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "recipes")

@NamedQueries({})
public class RecipeModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// TODO: add time
	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createDate;

	// TODO: add time
	// Date Only, no time part:
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date lastEdited;

	@Column(nullable = false, length = 30)
	private String title;

	@Column(nullable = false, length = 500)
	private String description;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserModel author;

	@OneToMany(mappedBy="recipe",fetch=FetchType.LAZY)
    private Set<IngredientAmountModel> ingredientAmounts;
	
	@Column(name = "published")
	private boolean published;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<CategorieModel> categories;
	
	@OneToMany(mappedBy="recipe",fetch=FetchType.LAZY)
    @OrderBy("createDate")
    private Set<CommentModel> comments;
	
	@ManyToMany(mappedBy = "reportedRecipes", fetch = FetchType.LAZY)
	private Set<UserModel> reportingUsers;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<RecipeCollectionModel> recipeCollections;
	
	@ManyToMany(mappedBy = "likedRecipes", fetch = FetchType.LAZY)
	private Set<UserModel> likingUsers;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PictureModel picture;

	@Version
	long version;

	public RecipeModel(Date createDate, Date lastEdited, String title, String description, UserModel author,
			Set<IngredientAmountModel> ingredientAmounts, boolean published, boolean enabled, Set<CategorieModel> categories) {
		super();
		this.createDate = createDate;
		this.lastEdited = lastEdited;
		this.title = title;
		this.description = description;
		this.author = author;
		this.ingredientAmounts = ingredientAmounts;
		this.published = published;
		this.enabled = enabled;
		this.categories = categories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}

	public Set<IngredientAmountModel> getIngredientAmounts() {
		return ingredientAmounts;
	}

	public void setIngredientAmounts(Set<IngredientAmountModel> ingredientAmounts) {
		this.ingredientAmounts = ingredientAmounts;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<CategorieModel> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategorieModel> categories) {
		this.categories = categories;
	}

	public Set<CommentModel> getComments() {
		return comments;
	}

	public void setComments(Set<CommentModel> comments) {
		this.comments = comments;
	}

	public Set<UserModel> getReportingUsers() {
		return reportingUsers;
	}

	public void setReportingUsers(Set<UserModel> reportingUsers) {
		this.reportingUsers = reportingUsers;
	}

	public Set<RecipeCollectionModel> getRecipeCollections() {
		return recipeCollections;
	}

	public void setRecipeCollections(Set<RecipeCollectionModel> recipeCollections) {
		this.recipeCollections = recipeCollections;
	}

	public Set<UserModel> getLikingUsers() {
		return likingUsers;
	}

	public void setLikingUsers(Set<UserModel> likingUsers) {
		this.likingUsers = likingUsers;
	}

	public PictureModel getPicture() {
		return picture;
	}

	public void setPicture(PictureModel picture) {
		this.picture = picture;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
