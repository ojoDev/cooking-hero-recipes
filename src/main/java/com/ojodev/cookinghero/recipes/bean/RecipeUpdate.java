package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * RecipeUpdate
 */
@Validated
public class RecipeUpdate   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("cousine_type")
  private String cousineType = null;

  @JsonProperty("length")
  private BigDecimal length = null;

  @JsonProperty("difficulty")
  private BigDecimal difficulty = null;

  public RecipeUpdate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * name of the recipe
   * @return name
  **/
  @ApiModelProperty(example = "spanish tortilla", value = "name of the recipe")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RecipeUpdate description(String description) {
    this.description = description;
    return this;
  }

  /**
   * general description of the recipe
   * @return description
  **/
  @ApiModelProperty(example = "classic Spanish omelette filled with pan-fried potatoes and onion.", value = "general description of the recipe")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RecipeUpdate cousineType(String cousineType) {
    this.cousineType = cousineType;
    return this;
  }

  /**
   * cousine type
   * @return cousineType
  **/
  @ApiModelProperty(example = "spanish", value = "cousine type")


  public String getCousineType() {
    return cousineType;
  }

  public void setCousineType(String cousineType) {
    this.cousineType = cousineType;
  }

  public RecipeUpdate length(BigDecimal length) {
    this.length = length;
    return this;
  }

  /**
   * cooking preparation time in minutes
   * @return length
  **/
  @ApiModelProperty(example = "60", value = "cooking preparation time in minutes")

  @Valid

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public RecipeUpdate difficulty(BigDecimal difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * difficult level
   * minimum: 1
   * maximum: 10
   * @return difficulty
  **/
  @ApiModelProperty(example = "6", value = "difficult level")

  @Valid
@DecimalMin("1") @DecimalMax("10") 
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
    RecipeUpdate recipeUpdate = (RecipeUpdate) o;
    return Objects.equals(this.name, recipeUpdate.name) &&
        Objects.equals(this.description, recipeUpdate.description) &&
        Objects.equals(this.cousineType, recipeUpdate.cousineType) &&
        Objects.equals(this.length, recipeUpdate.length) &&
        Objects.equals(this.difficulty, recipeUpdate.difficulty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, cousineType, length, difficulty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipeUpdate {\n");
    
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

