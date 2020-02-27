package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Request to create a new recipe.
 */
@ApiModel(description = "Request to create a new recipe.")
@Validated
@Data
public class RecipeNew {

    @JsonProperty("name")
    @ApiModelProperty(example = "Spanish tortilla", required = true, value = "Name of the recipe.")
    @NotNull
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(example = "Classic Spanish omelette filled with pan-fried potatoes and onion.", required = true, value = "General description of the recipe.")
    @NotNull
    private String description;

    @JsonProperty("cuisineType")
    @ApiModelProperty(value = "Recipe cuisine types.")
    @Valid
    private List<CuisineType> cuisineType;

    @JsonProperty("preparationTime")
    @ApiModelProperty(example = "15", value = "Ingredient preparation and cooking time in minutes (cut potatoes, fry, ...).")
    private Integer preparationTime;

    @JsonProperty("difficulty")
    @ApiModelProperty(example = "4", value = "Difficult level (1-5).")
    @Min(1)
    @Max(5)
    private Integer difficulty;

    @JsonProperty("mainImage")
    private Media mainImage;

    @JsonProperty("steps")
    @ApiModelProperty(value = "Ordered steps needed to cook the recipe.")
    @Valid
    private List<Step> steps;

    @JsonProperty("ingredients")
    @ApiModelProperty(value = "Ingredients included in the recipe.")
    @Valid
    private List<Ingredient> ingredients;

    @JsonProperty("user")
    @ApiModelProperty(example = "ojodev", value = "Owner username.")
    private String user;

}
