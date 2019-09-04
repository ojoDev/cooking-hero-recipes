package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Recipe created by a user
 */
@ApiModel(description = "Recipe created by a user")
@Validated
@Data
public class Recipe   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("cuisine-type")
  @Valid
  private List<String> cuisineType;

  @JsonProperty("preparation-time")
  private BigDecimal preparationTime;

  @JsonProperty("difficulty")
  private BigDecimal difficulty;

  @JsonProperty("photo")
  private PhotoRef photo;

  @JsonProperty("steps")
  @Valid
  private List<Step> steps;

  @JsonProperty("ingredients")
  @Valid
  private List<Ingredient> ingredients;

  @JsonProperty("user")
  private String user;

  @JsonProperty("creationDate")
  private DateTime creationDate;

}
