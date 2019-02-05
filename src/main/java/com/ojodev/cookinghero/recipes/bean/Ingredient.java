package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Ingredient
 */
@Validated
@Data
public class Ingredient {

	@JsonProperty("product")
	@ApiModelProperty(example = "potatoes", required = true, value = "product name")
	@NotNull
	private String product;

	@JsonProperty("quantity")
	@ApiModelProperty(example = "2", value = "number of product units or quantity")
	@Valid
	private BigDecimal quantity;

	@JsonProperty("unit")
	@ApiModelProperty(example = "gr", value = "type of measure")

	private String unit;

}
