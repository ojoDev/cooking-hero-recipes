package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Cuisine type name in a specific language.
 */
@ApiModel(description = "Cuisine type name in a specific language.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuisineTypeNewName {

    @JsonProperty("name")
    @ApiModelProperty(example = "veggie", value = "Descriptive cuisine type name.")
    private String name;

    @JsonProperty("language")
    @ApiModelProperty(value = "Language type.")
    private LanguageEnum language;

}
