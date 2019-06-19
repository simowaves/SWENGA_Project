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
import javax.persistence.JoinTable;
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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
 
	@Column(name = "userName", unique = true, nullable = false, length = 45)
	private String userName;
 
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "emailAddress", nullable = false, length = 60)
	private String emailAddress;
 
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
 
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	private Set<UserRoleModel> userRoles;
	
	@OneToMany(mappedBy="author",fetch=FetchType.EAGER)
    @OrderBy("title")
    private Set<RecipeModel> recipes;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinTable(name = "users_lovedIngredients")
	private Set<IngredientModel> lovedIngredients;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinTable(name = "users_hatedIngredients")
	private Set<IngredientModel> hatedIngredients;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private Set<AllergieModel> allergies;
	
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY)
    @OrderBy("createDate")
    private Set<CommentModel> comments;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	@JoinTable(name = "users_reportedRecipes")
	private Set<RecipeModel> reportedRecipes;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private Set<UserModel> usersIFollow;
	
	@ManyToMany(mappedBy = "usersIFollow", fetch = FetchType.EAGER)
	private Set<UserModel> usersFollowingMe;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
    @OrderBy("title")
    private Set<RecipeCollectionModel> recipeCollections;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
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
	
	public UserModel(String userName, String password, String emailAddress, boolean enabled,
			Set<AllergieModel> allergies) {
		super();
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.enabled = enabled;
		this.allergies = allergies;
	}
	
	public UserModel(String userName, String password, String emailAddress, boolean enabled,
			Set<AllergieModel> allergies,Set<IngredientModel> lovedIngredients, Set<IngredientModel> hatedIngredients ) {
		super();
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.enabled = enabled;
		this.allergies = allergies;
		this.lovedIngredients = lovedIngredients;
		this.hatedIngredients = hatedIngredients;
	}
	

	public UserModel(String userName, String password, String emailAddress, boolean enabled,
			Set<AllergieModel> allergies,Set<IngredientModel> lovedIngredients, Set<IngredientModel> hatedIngredients,Set<UserModel> usersIFollow, Set<UserModel> usersFollowingMe ) {
		super();
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.enabled = enabled;
		this.allergies = allergies;
		this.lovedIngredients = lovedIngredients;
		this.hatedIngredients = hatedIngredients;
		this.usersIFollow = usersIFollow;
		this.usersFollowingMe = usersFollowingMe;
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
 
	public Set<UserRoleModel> getUserRoles() {
		return userRoles;
	}
	
	public void addUserRole(UserRoleModel userRole) {
		if (userRoles==null) userRoles = new HashSet<UserRoleModel>();
		userRoles.add(userRole);
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
	
	public void addLovedIngredient(IngredientModel ingredient) {
		if (lovedIngredients==null) lovedIngredients = new HashSet<IngredientModel>();
		lovedIngredients.add(ingredient);
	}
	
	public void removeLovedIngredient(IngredientModel ingredient) {
		if (lovedIngredients==null) lovedIngredients = new HashSet<IngredientModel>();
		lovedIngredients.remove(ingredient);
	}

	public Set<IngredientModel> getHatedIngredients() {
		return hatedIngredients;
	}

	public void setHatedIngredients(Set<IngredientModel> hatedIngredients) {
		this.hatedIngredients = hatedIngredients;
	}
	
	public void addHatedIngredient(IngredientModel ingredient) {
		if (hatedIngredients==null) hatedIngredients = new HashSet<IngredientModel>();
		hatedIngredients.add(ingredient);
	}
	
	public void removeHatedIngredient(IngredientModel ingredient) {
		if (hatedIngredients==null) hatedIngredients = new HashSet<IngredientModel>();
		hatedIngredients.remove(ingredient);
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
	
	public void removeAllergie(AllergieModel allergie) {
		if (allergies==null) allergies = new HashSet<AllergieModel>();
		allergies.remove(allergie);
	}

	public Set<CommentModel> getComments() {
		return comments;
	}

	public void setComments(Set<CommentModel> comments) {
		this.comments = comments;
	}
	
	public void addComment(CommentModel comment) {
		if (comments==null) comments = new HashSet<CommentModel>();
		comments.add(comment);
	}

	public Set<RecipeModel> getReportedRecipes() {
		return reportedRecipes;
	}

	public void setReportedRecipes(Set<RecipeModel> reportedRecipes) {
		this.reportedRecipes = reportedRecipes;
	}
	
	public void addReportedRecipe(RecipeModel reportedRecipe) {
		if (reportedRecipes==null) reportedRecipes = new HashSet<RecipeModel>();
		reportedRecipes.add(reportedRecipe);
	}
	
	public void removeReportedRecipe(RecipeModel reportedRecipe) {
		if (reportedRecipes==null) reportedRecipes = new HashSet<RecipeModel>();
		reportedRecipes.remove(reportedRecipe);
	}

	public Set<UserModel> getUsersIFollow() {
		return usersIFollow;
	}

	public void setUsersIFollow(Set<UserModel> usersIFollow) {
		this.usersIFollow = usersIFollow;
	}
	
	public void addUserIFollow(UserModel user) {
		if (usersIFollow==null) usersIFollow = new HashSet<UserModel>();
		usersIFollow.add(user);
	}
	
	public void removeUserIFollow(UserModel user) {
		if (usersIFollow==null) usersIFollow = new HashSet<UserModel>();
		usersIFollow.remove(user);
	}

	public Set<UserModel> getUsersFollowingMe() {
		return usersFollowingMe;
	}

	public void setUsersFollowingMe(Set<UserModel> usersFollowingMe) {
		this.usersFollowingMe = usersFollowingMe;
	}
	
	public void addUserFollowingMe(UserModel user) {
		if (usersFollowingMe==null) usersFollowingMe = new HashSet<UserModel>();
		usersFollowingMe.add(user);
	}
	
	public void removeUserFollowingMe(UserModel user) {
		if (usersFollowingMe==null) usersFollowingMe = new HashSet<UserModel>();
		usersFollowingMe.remove(user);
	}

	public Set<RecipeCollectionModel> getRecipeCollections() {
		return recipeCollections;
	}

	public void setRecipeCollections(Set<RecipeCollectionModel> recipeCollections) {
		this.recipeCollections = recipeCollections;
	}
	
	public void addRecipeCollection(RecipeCollectionModel recipeCollection) {
		if (recipeCollections==null) recipeCollections = new HashSet<RecipeCollectionModel>();
		recipeCollections.add(recipeCollection);
	}

	public Set<RecipeModel> getLikedRecipes() {
		return likedRecipes;
	}

	public void setLikedRecipes(Set<RecipeModel> likedRecipes) {
		this.likedRecipes = likedRecipes;
	}
	
	public void addLikedRecipe(RecipeModel likedRecipe) {
		if (likedRecipes==null) likedRecipes = new HashSet<RecipeModel>();
		likedRecipes.add(likedRecipe);
	}
	
	public void removeLikedRecipe(RecipeModel likedRecipe) {
		if (likedRecipes==null) likedRecipes = new HashSet<RecipeModel>();
		likedRecipes.remove(likedRecipe);
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
		UserModel other = (UserModel) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
