package com.ojodev.cookinghero.recipes.bean;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RecipePhoto
 */
@Validated
@Data
public class RecipePhoto   {

  
  @JsonProperty("id")
  @ApiModelProperty(example = "88484351313003", required = true, value = "photo id")
  @NotNull
  private String id;
  
  @JsonProperty("main")
  @ApiModelProperty(required = true, value = "is the main photo of the recipe?")
  @NotNull
  private Boolean main;
  
}