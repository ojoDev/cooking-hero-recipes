package com.ojodev.cookinghero.recipes.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * step to cook a recipe
 */
@ApiModel(description = "step to cook a recipe")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {

	@JsonProperty("description")
	@ApiModelProperty(example = "In a large frying pan or skillet, heat olive oil over medium-high heat.", required = true, value = "description of ordered actions to cook the recipe", position = 0)
	@NotNull
	private String description;

	@JsonProperty("time")
	@ApiModelProperty(example = "15", value = "time in minutes to finish this step (cooking or/and preparation time)", position = 1)
	@Valid
	private BigDecimal time;

	@JsonProperty("media")
	@ApiModelProperty(value = "step visualization", position = 2)
	@Valid
	private Media media;

}
