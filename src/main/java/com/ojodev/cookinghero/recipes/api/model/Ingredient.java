package com.ojodev.cookinghero.recipes.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Ingredient
 */
@ApiModel(description = "Ingredient and its quantity used in a recipe.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

	@JsonProperty("id")
	@ApiModelProperty(example= "recipe01-potato", required = true, value = "Ingredient id.", position = 0)
	@NotNull
	@Valid
	private String id;

	@JsonProperty("product")
	@ApiModelProperty(required = true, value = "Product.", position = 1)
	@NotNull
	@Valid
	private Product product;

	@JsonProperty("quantity")
	@ApiModelProperty(example = "2", value = "Number of product units or quantity.", position = 2)
	@Valid
	@DecimalMin("0.01")
	private BigDecimal quantity;

	@JsonProperty("measure")
	@ApiModelProperty(example = "gr", value = "Type of measure.", position = 3)
	private Measure measure;

}

