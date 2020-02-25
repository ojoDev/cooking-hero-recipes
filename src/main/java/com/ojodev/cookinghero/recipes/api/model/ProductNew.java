package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Product of a ingredient.
 */
@ApiModel(description = "Product of a ingredient.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductNew {

    @JsonProperty("names")
    @ApiModelProperty(value = "List of product names and his languages.", position = 0)
    @Valid
    private List<ProductNewName> names;

    @ApiModelProperty(required = true, value = "Product status", position = 1)
    @NotNull
    @JsonProperty("status")
    private ProductStatusEnum status;

}
