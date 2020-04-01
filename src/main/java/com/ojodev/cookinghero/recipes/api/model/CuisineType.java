package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Cuisine type.
 */
@ApiModel(description = "Cuisine type.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuisineType {

    @JsonProperty("id")
    @ApiModelProperty(example = "15", required = true, value = "Cuisine type id.", position = 0)
    @NotNull
    private String id;

    @JsonProperty("name")
    @ApiModelProperty(example = "veggie", required = true, value = "Cuisine type name.", position = 1)
    @NotNull
    private String name;

}
