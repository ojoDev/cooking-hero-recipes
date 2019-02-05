package com.ojodev.cookinghero.recipes.bean;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ApiError
 */
@Validated
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ApiError {

	@JsonProperty("code")
	@ApiModelProperty(example = "NAME_NOT_VALID", value = "error code id")
	private String code;

	@JsonProperty("description")
	@ApiModelProperty(example = "Ingredient name is not valid", value = "error description")
	private String description;

}
