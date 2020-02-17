package com.ojodev.cookinghero.recipes.api.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ApiError
 */
@ApiModel(description = "Api error")
@Validated
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

	@JsonProperty("code")
	@ApiModelProperty(example = "NAME_NOT_VALID", value = "Error code id")
	private String code;

	@JsonProperty("description")
	@ApiModelProperty(example = "Ingredient name is not valid", value = "Error description")
	private String description;

}
