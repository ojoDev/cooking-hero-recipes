package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Measure used by ingredients.
 */
@ApiModel(description = "Measure used by ingredients.")
@Validated
@Data
public class Measure   {

  @JsonProperty("id")
  @ApiModelProperty(example = "g", required = true, value = "Measure id.")
  @NotNull
  private String id;

  @JsonProperty("name")
  @ApiModelProperty(example = "{\"singular\":\"g\",\"plural\":\"g\"}", required = true, value = "Measure description.")
  @NotNull
  private DescriptiveName name;

}
