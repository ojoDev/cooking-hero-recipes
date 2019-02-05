package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * RecipeRequest
 */
@Validated
public class RecipeRequest {

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("cousine-type")
	@Valid
	private List<String> cousineType;

	@JsonProperty("preparation-time")
	private BigDecimal preparationTime;

	@JsonProperty("cooking-time")
	private BigDecimal cookingTime;

	@JsonProperty("difficulty")
	private BigDecimal difficulty;

	@JsonProperty("photo")
	private PhotoRef photo;

	@JsonProperty("steps")
	@Valid
	private List<Step> steps;

	@JsonProperty("ingredients")
	@Valid
	private List<Ingredient> ingredients;

	@JsonProperty("user")
	private String user;

	public RecipeRequest name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 
	 * name of the recipe
	 * 
	 * 
	 * 
	 * 
	 * @return name
	 **/

	@ApiModelProperty(example = "spanish tortilla", required = true, value = "name of the recipe")

	@NotNull

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RecipeRequest description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * 
	 * general description of the recipe
	 * 
	 * 
	 * 
	 * 
	 * @return description
	 **/

	@ApiModelProperty(example = "classic Spanish omelette filled with pan-fried potatoes and onion.", required = true, value = "general description of the recipe")

	@NotNull

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RecipeRequest cousineType(List<String> cousineType) {
		this.cousineType = cousineType;
		return this;
	}

	public RecipeRequest addCousineTypeItem(String cousineTypeItem) {

		if (this.cousineType == null) {
			this.cousineType = new ArrayList<>();
		}

		this.cousineType.add(cousineTypeItem);
		return this;
	}

	/**
	 * 
	 * cousine type
	 * 
	 * 
	 * 
	 * 
	 * @return cousineType
	 **/

	@ApiModelProperty(example = "[\"spanish\",\"veggie\"]", value = "cousine type")

	public List<String> getCousineType() {
		return cousineType;
	}

	public void setCousineType(List<String> cousineType) {
		this.cousineType = cousineType;
	}

	public RecipeRequest preparationTime(BigDecimal preparationTime) {
		this.preparationTime = preparationTime;
		return this;
	}

	/**
	 * 
	 * ingredient preparation time in minutes (cut potatoes, ...)
	 * 
	 * 
	 * 
	 * 
	 * @return preparationTime
	 **/

	@ApiModelProperty(example = "15", required = true, value = "ingredient preparation time in minutes (cut potatoes, ...)")

	@NotNull

	@Valid

	public BigDecimal getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(BigDecimal preparationTime) {
		this.preparationTime = preparationTime;
	}

	public RecipeRequest cookingTime(BigDecimal cookingTime) {
		this.cookingTime = cookingTime;
		return this;
	}

	/**
	 * 
	 * cooking time in minutes (boil, ...)
	 * 
	 * 
	 * 
	 * 
	 * @return cookingTime
	 **/

	@ApiModelProperty(example = "40", value = "cooking time in minutes (boil, ...)")

	@Valid

	public BigDecimal getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(BigDecimal cookingTime) {
		this.cookingTime = cookingTime;
	}

	public RecipeRequest difficulty(BigDecimal difficulty) {
		this.difficulty = difficulty;
		return this;
	}

	/**
	 * 
	 * difficult level
	 * 
	 * 
	 * 
	 * minimum: 1
	 * 
	 * 
	 * maximum: 5
	 * 
	 * @return difficulty
	 **/

	@ApiModelProperty(example = "4", value = "difficult level")

	@Valid
	@DecimalMin("1")
	@DecimalMax("5")
	public BigDecimal getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(BigDecimal difficulty) {
		this.difficulty = difficulty;
	}

	public RecipeRequest photo(PhotoRef photo) {
		this.photo = photo;
		return this;
	}

	/**
	 * 
	 * 
	 * Get photo
	 * 
	 * 
	 * 
	 * @return photo
	 **/

	@ApiModelProperty(value = "")

	@Valid

	public PhotoRef getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoRef photo) {
		this.photo = photo;
	}

	public RecipeRequest steps(List<Step> steps) {
		this.steps = steps;
		return this;
	}

	public RecipeRequest addStepsItem(Step stepsItem) {

		if (this.steps == null) {
			this.steps = new ArrayList<>();
		}

		this.steps.add(stepsItem);
		return this;
	}

	/**
	 * 
	 * 
	 * Get steps
	 * 
	 * 
	 * 
	 * @return steps
	 **/

	@ApiModelProperty(value = "")

	@Valid

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public RecipeRequest ingredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
		return this;
	}

	public RecipeRequest addIngredientsItem(Ingredient ingredientsItem) {

		if (this.ingredients == null) {
			this.ingredients = new ArrayList<>();
		}

		this.ingredients.add(ingredientsItem);
		return this;
	}

	/**
	 * 
	 * 
	 * Get ingredients
	 * 
	 * 
	 * 
	 * @return ingredients
	 **/

	@ApiModelProperty(value = "")

	@Valid

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public RecipeRequest user(String user) {
		this.user = user;
		return this;
	}

	/**
	 * 
	 * owner user name
	 * 
	 * 
	 * 
	 * 
	 * @return user
	 **/

	@ApiModelProperty(example = "ojodev", value = "owner user name")

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RecipeRequest recipeRequest = (RecipeRequest) o;
		return Objects.equals(this.name, recipeRequest.name)
				&& Objects.equals(this.description, recipeRequest.description)
				&& Objects.equals(this.cousineType, recipeRequest.cousineType)
				&& Objects.equals(this.preparationTime, recipeRequest.preparationTime)
				&& Objects.equals(this.cookingTime, recipeRequest.cookingTime)
				&& Objects.equals(this.difficulty, recipeRequest.difficulty)
				&& Objects.equals(this.photo, recipeRequest.photo) && Objects.equals(this.steps, recipeRequest.steps)
				&& Objects.equals(this.ingredients, recipeRequest.ingredients)
				&& Objects.equals(this.user, recipeRequest.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, cousineType, preparationTime, cookingTime, difficulty, photo, steps,
				ingredients, user);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RecipeRequest {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    cousineType: ").append(toIndentedString(cousineType)).append("\n");
		sb.append("    preparationTime: ").append(toIndentedString(preparationTime)).append("\n");
		sb.append("    cookingTime: ").append(toIndentedString(cookingTime)).append("\n");
		sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
		sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
		sb.append("    steps: ").append(toIndentedString(steps)).append("\n");
		sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
		sb.append("    user: ").append(toIndentedString(user)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
