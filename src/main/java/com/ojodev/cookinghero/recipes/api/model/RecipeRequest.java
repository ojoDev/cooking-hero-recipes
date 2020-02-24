package com.ojodev.cookinghero.recipes.api.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * RecipeRequest
 */
@ApiModel(description = "request to create a new user")
@Validated
@Data
public class RecipeRequest {

	@JsonProperty("name")
	@ApiModelProperty(example = "spanish tortilla", required = true, value = "name of the recipe", position = 0)
	@NotNull
	private String name;

	@JsonProperty("description")
	@ApiModelProperty(example = "classic Spanish omelette filled with pan-fried potatoes and onion.", required = true, value = "general description of the recipe", position = 1)
	@NotNull
	private String description;

	@JsonProperty("cuisine-type")
	@ApiModelProperty(example = "[\"spanish\",\"veggie\"]", value = "cuisine type", position = 2)
	@Valid
	private List<String> cuisineType;

	@JsonProperty("preparation-time")
	@ApiModelProperty(example = "15", required = true, value = "ingredient preparation and cooking time in minutes (cut potatoes, ...)", position = 3)
	@NotNull
	private BigDecimal preparationTime;

	@JsonProperty("difficulty")
	@ApiModelProperty(example = "4", value = "difficult level", position = 4)
	@Valid
	@DecimalMin("1")
	@DecimalMax("5")
	private BigDecimal difficulty;

	@JsonProperty("photo")
	@ApiModelProperty(value = "recipe main photo", position = 5)
	@Valid
	private PhotoRef photo;

	@JsonProperty("steps")
	@ApiModelProperty(value = "ordered cooking steps", position = 6)
	@Valid
	private List<Step> steps;

	@JsonProperty("ingredients")
	@ApiModelProperty(value = "ingredients and its amounts", position = 7)
	@Valid
	private List<Ingredient> ingredients;

	@JsonProperty("user")
	@ApiModelProperty(example = "ojodev", value = "owner username", position = 8)
	private String user;
}
