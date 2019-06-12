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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class UserModel implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
 
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String userName;
 
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "emailAddress", nullable = false, length = 60)
	private String emailAddress;
 
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
 
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<UserRoleModel> userRoles;
	
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY)
    @OrderBy("title")
    private Set<RecipeModel> recipes;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<IngredientModel> lovedIngredients;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<IngredientModel> hatedIngredients;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<AllergieModel> allergies;
	
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY)
    @OrderBy("createDate")
    private Set<CommentModel> comments;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<RecipeModel> reportedRecipes;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<UserModel> usersIFollow;
	
	@ManyToMany(mappedBy = "usersIFollow", fetch = FetchType.LAZY)
	private Set<UserModel> usersFollowingMe;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
    @OrderBy("title")
    private Set<RecipeCollectionModel> recipeCollections;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<RecipeModel> likedRecipes;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PictureModel picture;
	
	@Version
	long version;
 
	public UserModel() {
	}
 
	public UserModel(String userName, String password, String emailAddress, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.enabled = enabled;
	}
 
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
 
	public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public boolean isEnabled() {
		return enabled;
	}
 
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
 
 
	public void setUserRoles(Set<UserRoleModel> userRoles) {
		this.userRoles = userRoles;
	}
 
	public void addUserRole(UserRoleModel userRole) {
		if (userRoles==null) userRoles = new HashSet<UserRoleModel>();
		userRoles.add(userRole);
	}
 
	public Set<UserRoleModel> getUserRoles() {
		return userRoles;
	}
 
	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);		
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<RecipeModel> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeModel> recipes) {
		this.recipes = recipes;
	}
	
	public void addRecipe(RecipeModel recipe) {
		if (recipes==null) recipes = new HashSet<RecipeModel>();
		recipes.add(recipe);
	}

	public Set<IngredientModel> getLovedIngredients() {
		return lovedIngredients;
	}

	public void setLovedIngredients(Set<IngredientModel> lovedIngredients) {
		this.lovedIngredients = lovedIngredients;
	}

	public Set<IngredientModel> getHatedIngredients() {
		return hatedIngredients;
	}

	public void setHatedIngredients(Set<IngredientModel> hatedIngredients) {
		this.hatedIngredients = hatedIngredients;
	}

	public Set<AllergieModel> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<AllergieModel> allergies) {
		this.allergies = allergies;
	}

	public Set<CommentModel> getComments() {
		return comments;
	}

	public void setComments(Set<CommentModel> comments) {
		this.comments = comments;
	}

	public Set<RecipeModel> getReportedRecipes() {
		return reportedRecipes;
	}

	public void setReportedRecipes(Set<RecipeModel> reportedRecipes) {
		this.reportedRecipes = reportedRecipes;
	}

	public Set<UserModel> getUsersIFollow() {
		return usersIFollow;
	}

	public void setUsersIFollow(Set<UserModel> usersIFollow) {
		this.usersIFollow = usersIFollow;
	}

	public Set<UserModel> getUsersFollowingMe() {
		return usersFollowingMe;
	}

	public void setUsersFollowingMe(Set<UserModel> usersFollowingMe) {
		this.usersFollowingMe = usersFollowingMe;
	}

	public Set<RecipeCollectionModel> getRecipeCollections() {
		return recipeCollections;
	}

	public void setRecipeCollections(Set<RecipeCollectionModel> recipeCollections) {
		this.recipeCollections = recipeCollections;
	}

	public Set<RecipeModel> getLikedRecipes() {
		return likedRecipes;
	}

	public void setLikedRecipes(Set<RecipeModel> likedRecipes) {
		this.likedRecipes = likedRecipes;
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
