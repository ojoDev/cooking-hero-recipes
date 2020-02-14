package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cuisine type fields to update
 */
@ApiModel(description = "Cuisine type fields to update")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuisineTypeUpdate   {

  @JsonProperty("name")
  @ApiModelProperty(example = "veggie", value = "Cuisine type name.")
  private String name;

}
