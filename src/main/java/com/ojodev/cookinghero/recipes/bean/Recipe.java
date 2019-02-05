package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Recipe created by a user
 */
@ApiModel(description = "Recipe created by a user")
@Validated
@Data
public class Recipe {

	@JsonProperty("id")
	@ApiModelProperty(example = "8899457821", required = true, value = "recipe id")
	@NotNull
	private String id;

	@JsonProperty("name")
	@ApiModelProperty(example = "spanish tortilla", required = true, value = "name of the recipe")
	@NotNull
	private String name;

	@JsonProperty("description")
	@ApiModelProperty(example = "classic Spanish omelette filled with pan-fried potatoes and onion.", required = true, value = "general description of the recipe")
	@NotNull
	private String description;

	@JsonProperty("cousine-type")
	@ApiModelProperty(example = "[\"spanish\",\"veggie\"]", required = true, value = "cousine type")
	@NotNull
	@Valid
	private List<String> cousineType = new ArrayList<>();

	@JsonProperty("preparation-time")
	@ApiModelProperty(example = "15", required = true, value = "ingredient preparation time in minutes (cut potatoes, ...)")
	@NotNull
	@Valid
	private BigDecimal preparationTime;

	@JsonProperty("cooking-time")
	@ApiModelProperty(example = "40", required = true, value = "cooking time in minutes (boil, ...)")
	@NotNull
	@Valid
	private BigDecimal cookingTime;

	@JsonProperty("difficulty")
	@ApiModelProperty(example = "4", required = true, value = "difficult level")
	@NotNull
	@Valid
	@DecimalMin("1")
	@DecimalMax("5")
	private BigDecimal difficulty;

	@JsonProperty("photo")
	@ApiModelProperty(value = "recipe main photo")
	@Valid
	private PhotoRef photo;

	@JsonProperty("steps")
	@ApiModelProperty(value = "steps to cook the recipe")
	@Valid
	private List<Step> steps ;
	
	@JsonProperty("ingredients")
	@ApiModelProperty(value = "ingredients included in the recipe")
	@Valid
	private List<Ingredient> ingredients;

	@JsonProperty("user")
	@ApiModelProperty(value = "owner username", example = "ojodev")
	private String user;

	@JsonProperty("creationDate")
	@ApiModelProperty(value = "ingredients included in the recipe", example = "2019-01-23T17:32:28Z'")
	private DateTime creationDate;

}
