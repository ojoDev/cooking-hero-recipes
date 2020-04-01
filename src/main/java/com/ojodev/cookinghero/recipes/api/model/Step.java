package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Step to cook a recipe.
 */
@ApiModel(description = "Step to cook a recipe.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {

	@JsonProperty("id")
	@ApiModelProperty(example = "1", required = true, value = "Position of step in a recipe.", position = 0)
	@NotNull
	private Integer id;

	@JsonProperty("description")
	@ApiModelProperty(example = "In a large frying pan or skillet, heat olive oil over medium-high heat.", required = true, value = "Description of ordered actions to cook the recipe.", position = 1)
	@NotNull
	private String description;

	@JsonProperty("media")
	@ApiModelProperty(value = "Step visualization.", position = 2)
	@Valid
	private MediaRef media;

}
