package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Recipe created by a user
 */
@ApiModel(description = "Recipe created by a user.")
@Validated
@Data
public class Recipe {

    @JsonProperty("id")
    @ApiModelProperty(example = "8899457821", required = true, value = "Recipe id.")
    @NotNull
    private String id;

    @JsonProperty("name")
    @ApiModelProperty(example = "Spanish tortilla", required = true, value = "Name of the recipe.")
    @NotNull
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(example = "Classic Spanish omelet filled with pan-fried potatoes and onion.", required = true, value = "General description of the recipe.")
    @NotNull
    private String description;

    @JsonProperty("cuisineTypes")
    @ApiModelProperty(value = "Recipe cuisine types.")
    @Valid
    private List<CuisineType> cuisineTypes;

    @JsonProperty("preparationTime")
    @ApiModelProperty(example = "15", value = "Ingredient preparation and cooking time in minutes (cut potatoes, fry, ...).")
    private Integer preparationTime;

    @JsonProperty("difficulty")
    @ApiModelProperty(example = "4", required = true, value = "Difficult level (1-5).")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer difficulty;

    @JsonProperty("mainImage")
    @ApiModelProperty(value = "Main image of the recipe. Needs to be an image.")
    private Media mainImage;

    @JsonProperty("steps")
    @ApiModelProperty(value = "Ordered steps needed to cook the recipe.")
    @Valid
    private List<Step> steps;

    @JsonProperty("ingredients")
    @ApiModelProperty(value = "Ingredients included in the recipe.")
    @Valid
    private List<Ingredient> ingredients;

    @JsonProperty("userId")
    @ApiModelProperty(example = "ojodev", value = "Owner user id.")
    private String userId;

    @JsonProperty("creationDate")
    @ApiModelProperty(example = "2019-01-23T17:32:28Z", value = "Recipe creation date.")
    private DateTime creationDate;

    @JsonProperty("updateDate")
    @ApiModelProperty(example = "2019-04-23T21:20:55Z", value = "Recipe las update date.")
    @Valid
    private DateTime updateDate;

    @JsonProperty("status")
    @ApiModelProperty(value = "Recipe status")
    private RecipeStatusEnum status;

}
