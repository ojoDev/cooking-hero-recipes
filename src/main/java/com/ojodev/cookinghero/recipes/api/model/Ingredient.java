package com.ojodev.cookinghero.recipes.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Ingredient
 */
@ApiModel(description = "ingredient and its quantity used in a recipe")
@Validated
@Data
public class Ingredient {

	@JsonProperty("product")
	@ApiModelProperty(example = "potatoes", required = true, value = "product name", position = 0)
	@NotNull
	private String product = "";

	@JsonProperty("quantity")
	@ApiModelProperty(example = "2", value = "number of product units or quantity", position = 1)
	@Valid
	private BigDecimal quantity;

	@JsonProperty("measure")
	@ApiModelProperty(example = "gr", value = "type of measure", position = 2)
	private String measure;

	public Ingredient() {
		super();
	}

	public Ingredient(@NotNull String product) {
		super();
		this.product = product;
	}

	public Ingredient(@NotNull String product, @Valid BigDecimal quantity, String measure) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.measure = measure;
	}

}
