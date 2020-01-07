package com.ojodev.cookinghero.recipes.api.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Define a name to describe a item associated with a quantity, in singular or plural form.
 */
@ApiModel(description = "Define a name to describe a item associated with a quantity, in singular or plural form.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptiveName   {

    @JsonProperty("singular")
    @ApiModelProperty(example = "potato", value = "Singular name.")
    private String singular;

    @JsonProperty("plural")
    @ApiModelProperty(example = "potatoes", value = "Plural name.")
    private String plural;


}
