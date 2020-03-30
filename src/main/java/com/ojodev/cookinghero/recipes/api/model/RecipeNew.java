package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Request to create a new recipe.
 */
@ApiModel(description = "Request to create a new recipe.")
@Data
public class RecipeNew {

    @JsonProperty("name")
    @ApiModelProperty(example = "Spanish tortilla", required = true, value = "Name of the recipe.")
    @NotNull
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(example = "Classic Spanish omelet filled with pan-fried potatoes and onion.", value = "General description of the recipe.")
    private String description;

    @JsonProperty("preparationTime")
    @ApiModelProperty(example = "15", value = "Ingredient preparation and cooking time in minutes (cut potatoes, fry, ...).")
    private Integer preparationTime;

    @JsonProperty("difficulty")
    @ApiModelProperty(example = "4", value = "Difficult level (1-5).")
    @Min(1)
    @Max(5)
    private Integer difficulty;

    @JsonProperty("user")
    @ApiModelProperty(example = "ojodev", value = "Owner username.")
    private String user;

}

