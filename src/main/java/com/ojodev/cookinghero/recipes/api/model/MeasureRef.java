package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Measure reference.
 */
@ApiModel(description = "Measure reference.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasureRef {

    @JsonProperty("id")
    @ApiModelProperty(example = "kg", value = "Measure id, related to a existent measure.")
    private String id;

}
