package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Recipe fields to update.
 */
@ApiModel(description = "Recipe fields to update.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-02-27T07:48:56.931Z[GMT]")
public class RecipeUpdate {

    @JsonProperty("name")
    @ApiModelProperty(example = "Spanish tortilla", value = "Name of the recipe.")
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(example = "Classic Spanish omelette filled with pan-fried potatoes and onion.", value = "General description of the recipe.")
    private String description;

    @JsonProperty("preparationTime")
    @ApiModelProperty(example = "15", value = "Ingredient preparation and cooking time in minutes (cut potatoes, fry, ...).")
    private Integer preparationTime;

    @JsonProperty("difficulty")
    @ApiModelProperty(example = "4", value = "Difficult level (1-5).")
    @Min(1)
    @Max(5)
    private Integer difficulty;

    @JsonProperty("mainImage")
    @ApiModelProperty(value = "Main image of the recipe. Needs to be an image.")
    private MediaRef mainImage;

    @JsonProperty("status")
    @ApiModelProperty(value = "Recipe status")
    private RecipeStatusEnum status;

}
