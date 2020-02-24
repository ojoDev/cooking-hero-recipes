package com.ojodev.cookinghero.recipes.api.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Recipe created by a user
 */
@ApiModel(description = "recipe created by a user")
@Validated
@Data
public class Recipe {

    @JsonProperty("id")
    @ApiModelProperty(example = "8899457821", required = true, value = "recipe id", position = 0)
    @NotNull
    private String id;

    @JsonProperty("name")
    @ApiModelProperty(example = "spanish tortilla", required = true, value = "name of the recipe", position = 1)
    @NotNull
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(example = "classic Spanish omelette filled with pan-fried potatoes and onion.", required = true, value = "general description of the recipe", position = 2)
    @NotNull
    private String description;

    @JsonProperty("cuisine-type")
    @ApiModelProperty(example = "[\"spanish\",\"veggie\"]", value = "cuisine type", position = 3)
    @Valid
    private List<String> cuisineType;

    @JsonProperty("preparation-time")
    @ApiModelProperty(example = "15", required = true, value = "ingredient preparation and cooking time in minutes (cut potatoes, ...)", position = 4)
    @NotNull
    @Valid
    private BigDecimal preparationTime;

    @ApiModelProperty(example = "4", required = true, value = "difficult level", position = 5)
    @NotNull
    @Valid
    @DecimalMin("1")
    @DecimalMax("5")
    @JsonProperty("difficulty")
    private BigDecimal difficulty;

    @JsonProperty("photo")
    @ApiModelProperty(value = "recipe main photo", position = 6)
    private PhotoRef photo;

    @JsonProperty("steps")
    @ApiModelProperty(value = "ordered cooking steps", position = 7)
    @Valid
    private List<Step> steps;

    @JsonProperty("ingredients")
    @ApiModelProperty(value = "ingredients and its amounts ", position = 8)
    @Valid
    private List<Ingredient> ingredients;

    @JsonProperty("user")
    @ApiModelProperty(example = "ojodev", value = "owner user name", position = 9)
    private String user;

    @JsonProperty("creationDate")
    @ApiModelProperty(example = "2019-01-23T17:32:28Z", value = "recipe creation date", position = 10)
    @Valid
    private DateTime creationDate;

}
