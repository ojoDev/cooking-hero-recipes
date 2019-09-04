package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * RecipeRequest
 */
@Validated
@Data
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
}
