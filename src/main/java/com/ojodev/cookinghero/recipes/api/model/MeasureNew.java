package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Measure used by ingredients.
 */
@ApiModel(description = "Measure used by ingredients.")
@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasureNew   {

  @JsonProperty("names")
  @ApiModelProperty(value = "List of measure names and his languages.", required = true)
  private List<MeasureNewName> names;


}
