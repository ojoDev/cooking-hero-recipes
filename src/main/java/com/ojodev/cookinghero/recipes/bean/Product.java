package com.ojodev.cookinghero.recipes.bean;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Product
 */
@Validated
@Data
public class Product {

	@JsonProperty("name")
	@ApiModelProperty(example = "potatoe", required = true, value = "product name")
	@NotNull
	private String name;

}
