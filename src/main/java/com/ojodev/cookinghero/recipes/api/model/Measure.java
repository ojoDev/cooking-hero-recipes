package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Measure used by ingredients.
 */
@ApiModel(description = "Measure used by ingredients.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measure   {

  @JsonProperty("id")
  @ApiModelProperty(example = "g", required = true, value = "Measure id.", position = 0)
  @NotNull
  private String id;

  @JsonProperty("name")
  @ApiModelProperty(example = "{\"singular\":\"g\",\"plural\":\"g\"}", required = true, value = "Measure description.", position = 1)
  @NotNull
  private DescriptiveName name;

}
