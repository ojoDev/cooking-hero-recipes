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
 * Recipe
 */
@Validated
public class Recipe {

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("cousine-type")
	@Valid
	private List<String> cousineType = new ArrayList<>();

	@JsonProperty("length")
	private BigDecimal length = null;

	@JsonProperty("difficulty")
	private BigDecimal difficulty = null;

	public Recipe id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * 
	 * id of the recipe. Only used in get operations
	 * 
	 * 
	 * 
	 * 
	 * @return id
	 **/
	@ApiModelProperty(example = "5c0960ba49a1703ca0831124", value = "id of the recipe. Only used in get operations")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Recipe name(String name) {
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

	public Recipe description(String description) {
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

	public Recipe cousineType(List<String> cousineType) {
		this.cousineType = cousineType;
		return this;
	}

	public Recipe addCousineTypeItem(String cousineTypeItem) {

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

	@ApiModelProperty(example = "spanish", required = true, value = "cousine type")

	@NotNull

	public List<String> getCousineType() {
		return cousineType;
	}

	public void setCousineType(List<String> cousineType) {
		this.cousineType = cousineType;
	}

	public Recipe length(BigDecimal length) {
		this.length = length;
		return this;
	}

	/**
	 * 
	 * cooking preparation time in minutes
	 * 
	 * 
	 * 
	 * 
	 * @return length
	 **/

	@ApiModelProperty(example = "60", required = true, value = "cooking preparation time in minutes")

	@NotNull

	@Valid

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public Recipe difficulty(BigDecimal difficulty) {
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
	 * maximum: 10
	 * 
	 * @return difficulty
	 **/

	@ApiModelProperty(example = "6", required = true, value = "difficult level")

	@NotNull

	@Valid
	@DecimalMin("1")
	@DecimalMax("10")
	public BigDecimal getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(BigDecimal difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Recipe recipe = (Recipe) o;
		return Objects.equals(this.id, recipe.id) && Objects.equals(this.name, recipe.name)
				&& Objects.equals(this.description, recipe.description)
				&& Objects.equals(this.cousineType, recipe.cousineType) && Objects.equals(this.length, recipe.length)
				&& Objects.equals(this.difficulty, recipe.difficulty);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, cousineType, length, difficulty);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Recipe {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    cousineType: ").append(toIndentedString(cousineType)).append("\n");
		sb.append("    length: ").append(toIndentedString(length)).append("\n");
		sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
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
