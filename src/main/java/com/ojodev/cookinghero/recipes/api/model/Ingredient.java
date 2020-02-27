package com.ojodev.cookinghero.recipes.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
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

	@JsonProperty("product")
	@ApiModelProperty(required = true, value = "Product.", position = 0)
	@NotNull
	@Valid
	private Product product;

	@JsonProperty("quantity")
	@ApiModelProperty(example = "2", value = "Number of product units or quantity.", position = 1)
	@Valid
	@Min(1)
	private BigDecimal quantity;

	@JsonProperty("measure")
	@ApiModelProperty(example = "gr", value = "Type of measure.", position = 2)
	private Measure measure;

}

