package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Product name in a specific language.
 */
@ApiModel(description = "Product name in a specific language.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductNewName {

    @JsonProperty("name")
    @ApiModelProperty(example = "{\"singular\":\"potato\",\"plural\":\"potatoes\"}", value = "Descriptive product name.")
    private DescriptiveName name;

    @JsonProperty("language")
    @ApiModelProperty(value = "Product language")
    private LanguageEnum language;

}
