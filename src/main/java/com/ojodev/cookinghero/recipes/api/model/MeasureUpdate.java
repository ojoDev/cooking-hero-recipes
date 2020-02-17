package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Measure used by ingredients.
 */
@ApiModel(description = "Measure used by ingredients.")
@Validated
@Data
public class MeasureUpdate   {

  @JsonProperty("name")
  @ApiModelProperty(example = "{\"singular\":\"g\",\"plural\":\"g\"}", value = "Measure description.")
  private DescriptiveName name;

}
