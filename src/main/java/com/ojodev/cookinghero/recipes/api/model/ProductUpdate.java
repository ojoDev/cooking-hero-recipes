package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Product fields to update.
 */
@ApiModel(description = "Product fields to update.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdate {

    @JsonProperty("name")
    @ApiModelProperty(example = "{\"singular\":\"potato\",\"plural\":\"potatoes\"}", value = "Measure description.")
    private DescriptiveName name;

    @JsonProperty("status")
    @ApiModelProperty(value = "Product status")
    private ProductStatusEnum status;

}
