package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Measure name in a specific language.
 */
@ApiModel(description = "Measure name in a specific language.")
@Validated
@Data
public class MeasureNewName   {

  @JsonProperty("name")
  @ApiModelProperty(example = "{\"singular\":\"g\",\"plural\":\"g\"}", value = "Descriptive measure name.", required = true)
  private DescriptiveName name;

  @JsonProperty("language")
  @ApiModelProperty(value = "Language type.", required = true)
  private LanguageEnum language;

}
