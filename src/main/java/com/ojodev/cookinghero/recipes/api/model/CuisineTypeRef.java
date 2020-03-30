package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Cuisine type reference.
 */
@ApiModel(description = "Cuisine type reference.")
@Data
public class CuisineTypeRef {

    @JsonProperty("id")
    @ApiModelProperty(example = "veggie", value = "Cuisine type id, related to a existent cuisine type.")
    private String id;

}

